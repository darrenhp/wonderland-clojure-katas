(ns tiny-maze.solver)


(defn change [maze x y e]
  (assoc maze x (assoc (maze x) y e)))
  

(defn solve-maze [maze]
  (let [X (dec (count maze))
        Y (dec (count (maze 0)))]
    (loop [[cur-x cur-y] [0 0]
           maze maze
           reached {[cur-x cur-y] [-1 -1]}]
      (if (and (= cur-x X)
               (= cur-y Y))
          (change maze cur-x cur-y :x)
          (if-let [[x y] (first (filter (fn [[x y]] (and (<= 0 x X)
                                                         (<= 0 y Y)
                                                         (or (= 0 ((maze x) y))
                                                             (= :E ((maze x) y)))
                                                         (not (reached [x y]))))
                                        [[(inc cur-x) cur-y]
                                         [(dec cur-x) cur-y]
                                         [cur-x (inc cur-y)]
                                         [cur-x (dec cur-y)]]))]
                  (recur [x y] 
                         (change maze cur-x cur-y :x)
                         (assoc reached [x y] [cur-x cur-y]))
                  (let [[last-x last-y] (reached [cur-x cur-y])]
                       (recur [last-x last-y]
                              (change maze cur-x cur-y 0)
                              reached)))))))             
                                 
      
      
      
    
    
    

         
         
  
  
