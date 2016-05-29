(ns wonderland-number.finder)

(defn wonderland-number []
    (first (filter (fn [n] 
                       (apply = 
                              (map #(sort (str (* n %)))  
                                   `(1 2 3 4 5 6)))) 
                   (range 100000 1000000))))


(defn wonderland2-number []
     (filter (fn [n] 
                 (< (reduce + (map #(* %1 %1 %1) 
                                   (map #(- (int %) (int \0)) 
                                        (str n)))) 
                    1000))
             (range 100000 1000000)))
