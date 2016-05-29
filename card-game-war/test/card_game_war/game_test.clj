(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= :player1-win (play-round [:spade 3 ] [:spade 2]))))
  (testing "queens are higher rank than jacks"
    (is (= :player1-win (play-round [:spade :queen] [:spade :jack]))))
  (testing "kings are higher rank than queens"
    (is (= :player2-win (play-round [:spade :queen] [:spade :king]))))
  (testing "aces are higher rank than kings"
    (is (= :player2-win (play-round [:spade :king] [:spade :ace]))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= :player2-win (play-round [:spade :king] [:club :king]))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= :player2-win (play-round [:club :king] [:diamond :king]))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= :player2-win (play-round [:diamond :king] [:heart :king])))))

(deftest test-play-game
  (testing "the player1 loses when they run out of cards"
    (is (= :player1-win (play-game [[:spade 3]] []))))
  (testing "the player2 loses when they run out of cards"
    (is (= :player2-win (play-game [] [[:spade 3]]))))
  (testing "the draw when all empty"
    (is (= :draw (play-game [] []))))
  (testing "the draw when they have same cards"
    (is (= :draw (play-game [[:spade 3]] [[:spade 3]]))))
  (testing "the player1"
    (is (= :player1-win (play-game [[:spade 4]]
                                   [[:spade 3] [:spade 4]])))))

