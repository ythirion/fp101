(ns syntax)

; VARIABLES
; Mutable values (atoms)
(def x (atom 5))
(reset! x 6)

; Immutables values
(def x 5)

(def language "Scala")
(def sheet-name (str "Let's learn " language 
                     " basics"))

(def x ^Double 5)

; FUNCTIONS
; Define function
(defn square [x] (* x x))

; Use default parameter
(defn square 
   ([] (square 4))
   ([x] (* x x)))

; Anonymous functions
(fn [x] (* x x))

; passing a function
(map square (range 1 5))

; passing an anonymous function
(map (fn [x] (* x x)) (range 1 5))

; passing shortcut function
(map #(* %1 %1) (range 1 5))

; last value is returned (y)
(map (fn [x]) 
  (let [y (* x 2)]
     (println y)
     y) 
  (range 1 5))

; passing shortcut function
(map #(* %1 2) 
   (filter #(zero? (mod %1 2)) (range 1 5)))

; easier to read
(->> (range 1 5)
     (filter #(zero? (mod %1 2)))
     (map #(* %1 2)))

; Function composition
(defn minus1-times2 [x]
  (comp #(* x 2) dec))
(println (minus1-times2 4))

; Every function can be currified
; using the function “partial”
(defn sum [x y] (+ x y))
(defn sum2 [x] (partial sum 2))

; DATA STRUCTURES
; Lists / Vectors are tuple
[1 2 3]
(let [[x y z] [1 2 3]]
   (println x))

; Everything is immutable
(def xs [1 2 3])
(def ys [1 2 3])

; Indexing
(get xs 2)

; Cons
(conj '(2 3) 1)
(conj  [1 2] 3)

; CONTROL CONSTRUCTS
; Lists / Vectors are tuple
(defn happy [] (println ";-)"))
(defn sad [] (println ":-("))

; Conditional
(if true happy sad)
(when true happy)

; while
(def x (atom …))
(while (< @x 5) 
  (println x)
  (swap! x inc))

; For loop
(for [x xs :when (zero? (mod x 2))]
  (* x 10))

(->> xs
     (filter #(zero? (mod %1 2)))
     (map #(* %1 2)))

; PATTERN MATCHING
(require '[clojure.core.match :refer [match]])
(def v42 42)(match [v42] 
              [42] (println "42")
                    :else (println "Not 42"))

; IMMUTABLE DATA STRUCTURE (Record)
;all constructor parameters are public and immutable
(defrecord Student [name year])
(defrecord Teacher [name specialty])

(defmulti person-to-string class) 
(defmethod person-to-string Student [{:keys [name year]}] 
    (str name " is a student in Year " year ".")) 
(defmethod person-to-string Teacher [{:keys [name specialty]}] 
    (str name " teaches " specialty "."))

; Data equality
(def bart1 [->Student "Bart Simpson" 2021])
(def bart2 [->Student "Bart Simpson" 2021])
(= bart1 bart2)

