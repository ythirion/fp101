(ns domain)

(def people [{:firstname "Mary" :lastname "Smith" :pets [{:type :cat :name "Tabby" :age 2}]}
             {:firstname "Bob" :lastname "Smith" :pets [{:type :cat :name "Dolly" :age 3}
                                                        {:type :dog :name "Spot" :age 2}]}
             {:firstname "Ted" :lastname "Smith" :pets [{:type :dog :name "Spike" :age 4}]}
             {:firstname "Jake" :lastname "Snake" :pets [{:type :snake :name "Serpy" :age 1}]}
             {:firstname "Barry" :lastname "Bird" :pets [{:type :bird :name "Tweety" :age 2}]}
             {:firstname "Terry" :lastname "Turtle" :pets [{:type :turtle :name "Speedy" :age 1}]}
             {:firstname "Harry" :lastname "Hamster" :pets [{:type :hamster :name "Fuzzy" :age 1}
                                                            {:type :hamster :name "Wuzzy" :age 1}]}
             {:firstname "John" :lastname "Doe"}])

(def parks [{:name "Jurassic" :authorized-pets #{:bird :snake :turtle}}
            {:name "Central" :authorized-pets #{:bird :cat :dog}}
            {:name "Hippy" :authorized-pets #{:bird :cat :dog :turtle :hamster :snake}}])