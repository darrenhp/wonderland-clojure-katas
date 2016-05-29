(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.set]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn is-near [word1 word2]
  (and (= (count word1) (count word2))
       (= 1 
          (count (filter true? (map not= word1 word2))))))


(defn near-words [word]
  (into #{} (filter (partial is-near word) words)))



(defn doublets [word1 word2]
  (loop [cur word1 
         path [word1] 
         used #{word1}]
    (if (= cur word2)
      path
      
      (let [prev (if (= (count path) 1) nil (nth path (- (count path) 2)))
            next-words (clojure.set/difference (near-words cur) used)
            next (first next-words)]
           (cond next (recur next (conj path next) (conj used next))
                 prev (recur prev (into [] (drop-last 1 path)) used)
                 true [])))))
      
      
  
