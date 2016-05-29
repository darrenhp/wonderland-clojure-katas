(ns magic-square.puzzle)

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])
    
  
(defn magic-square [values]
  (let [mid (/ (apply + values) 3)]
    (loop []
      (let [a00 (nth values (rand-int 9))
            a01 (nth values (rand-int 9))
            a02 (- mid a00 a01)
            a10 (nth values (rand-int 9))
            a11 (- (+ a00 a10) a02)
            a12 (- mid a10 a11)
            a20 (- mid a00 a10)
            a21 (- mid a01 a11)
            a22 (- mid a02 a12)]
         (if (and (= (sort values) (sort [a00 a01 a02 a10 a11 a12 a20 a21 a22]))
                  (= mid (+ a20 a21 a22) (+ a00 a11 a22)))
             [[a00 a01 a02] [a10 a11 a12] [a20 a21 a22]]
             (recur))))))
        
         
     
