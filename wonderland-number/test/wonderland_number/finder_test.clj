(ns wonderland-number.finder-test
  (:require [clojure.test :refer :all]
            [wonderland-number.finder :refer :all]))

(defn hasAllTheSameDigits? [n1 n2]
  (let [s1 (set (str n1))
        s2 (set (str n2))]
    (= s1 s2)))

(deftest test-wonderland-number
  (testing "A wonderland number must have the following things true about it"
    (let [wondernum (wonderland-number)]
      (is (= 6 (count (str wondernum))))
      (is (hasAllTheSameDigits? wondernum (* 2 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 3 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 4 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 5 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 6 wondernum))))))


(defn is-wonder2-valid[n]
      (< (reduce + (map #(* %1 %1 %1) 
                         (map #(- (int %) (int \0)) 
                              (str n))))
         10))

(deftest test-wonderland2-number
  (testing "A wonderland2 number must have the following things true about it"
    (let [wondernum (wonderland2-number)]
      (map #(is (is-wonder2-valid %) wondernum)))))