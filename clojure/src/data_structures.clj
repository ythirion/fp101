(ns data-structures
  (:require [domain :refer :all]
            [clojure.string :as str]))

(println "1. What are the first names of all people ?")
(map :firstname people)


(println "2. Who owns Cats ?")
(defn has-pet-type [pet-type person]
  (some #{pet-type} (->> person :pets (map :type))))
(filter #(has-pet-type :cat %) people)


(println "3. What are the names of Mary Smiths cats ?")
(defn full-name [person]
  (str (:firstname person) " " (:lastname person)))
(->> people (filter #(= "Mary Smith" (full-name %))) first :pets (map :name))


(println "4. Do all people have pets ?")
(every? :pets people)


(println "5. How many people have a given type of pet ?")
(defn count-by-pet-type [pet-type persons]
  (count (filter #(has-pet-type pet-type %) persons)))
(count-by-pet-type :cat people)


(println "6. How many pet types of all people ?")
(->> people (map :pets) flatten (map :type) set count)


(println "7. Who owns the youngest Pet ?")
(defn has-pet [person] (contains? person :pets))
(defn min-pet-age [person] (->> person :pets (mapv :age) min))
(->> people (filter has-pet) (sort-by min-pet-age) first)


(println "8. What is the average Pet age ?")
(defn pets-age [person] (->> person :pets (mapv :age)))
(defn average [coll] (/ (apply + coll) (count coll)))
(->> people (map pets-age) flatten average)


(println "9. What are all the pets name sorted alphabetically ?")
(defn pets-name [person] (->> person :pets (mapv :name)))
(->> people (map pets-name) flatten sort (str/join ", "))


(println "10. What are the parks in which each person can walk with all their pets ?")
(defn pets-type [person] (->> person :pets (mapv :type) set))
(defn can-walk [person park] (every? (:authorized-pets park) (pets-type person)))
(let [m (group-by full-name people)]
  (apply merge
         (map (fn [[k v]] {k (map :name (filter #(can-walk (first v) %) parks))})
              m)))