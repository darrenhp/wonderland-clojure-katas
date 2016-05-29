(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))
(def sorted-cards 
  (for [rank ranks
        suit suits]
    [suit rank]))
(defn play-round [player1-card player2-card]
  (let [i1 (.indexOf sorted-cards  player1-card)
        i2 (.indexOf sorted-cards  player2-card)]
     (cond (> i1 i2) :player1-win
           (< i1 i2) :player2-win
           true      :draw)))

(defn play-game [player1-cards player2-cards]
  (loop [[c1 & cs1] player1-cards 
         [c2 & cs2] player2-cards]
    (let [a1 (into [] cs1)
          a2 (into [] cs2)]
      (cond (and (nil? c1) (nil? c2)) :draw
            (nil? c1) :player2-win
            (nil? c2) :player1-win
            true (case (play-round c1 c2)
                       :player1-win (recur (conj a1 c1 c2) a2)
                       :player2-win (recur a1 (conj a2 c1 c2))
                       :draw (recur a1 a2))))))  
      
          
          
    
    
