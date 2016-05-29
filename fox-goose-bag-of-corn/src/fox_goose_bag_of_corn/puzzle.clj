(ns fox-goose-bag-of-corn.puzzle
  (:require [clojure.set]))

(def start-pos [[#{:fox :goose :corn :you} #{:boat} #{}]])

(defn valid-state? [[L M R]]
   (not (or (L :boat)
            (R :boat)
            (and (L :fox) 
                 (L :goose)
                 (not (L :you)))
            (and (L :goose)
                 (L :corn)
                 (not (L :you)))
            (and (R :fox) 
                 (R :goose)
                 (not (R :you)))
            (and (R :goose)
                 (R :corn)
                 (not (R :you)))
            (L :boat)
            (R :boat)
            (> (count M) 3)
            (and (= (count M) 2)
                 (not (M :you))))))
     
(def end-state [#{} #{:boat} #{:fox :goose :corn :you}])

(defn spawn-states [[L M R]]
  (cond
    (L :you) (into #{} (map (fn [x] [(disj L x :you)
                                     (conj M x :you)
                                     R])
                            L))
    (R :you) (into #{} (map (fn [x] [L
                                     (conj M x :you)
                                     (disj R x :you)])
                            R))
    (M :you) (clojure.set/union (into #{} (map (fn [x] [(conj L x :you)
                                                        (disj M x :you)
                                                        R])
                                               M))
                                (into #{} (map (fn [x] [L
                                                        (disj M x :you)
                                                        (conj R x :you)])
                                               M)))))

(defn spawn-valid-states [S]
  (filter valid-state? (spawn-states S)))
  
(defn river-crossing-plan []
  (loop [reached {(first start-pos) start-pos}
         unspawned #{(first start-pos)}]
    (if (contains? reached end-state)
        (get reached end-state)
        (let [cur-state  (first unspawned)
              new-states (filter #(not (contains? reached %))
                                 (spawn-valid-states cur-state))]
             (recur (merge reached (zipmap new-states 
                                           (map #(conj (get reached cur-state) %) new-states)))
               (clojure.set/union (disj unspawned cur-state) (into #{} new-states)))))))
          

