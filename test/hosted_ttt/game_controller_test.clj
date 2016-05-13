(ns hosted-ttt.game-controller-test
  (:require [clojure.test :refer :all]
            [hosted-ttt.game-controller :as game-controller]))

(deftest human-move-test
  (testing "returns a board with the given move if there are two human players"
    (is (= {1 "X"} (game-controller/human-move {} 1 2))))
  (testing "returns the same board if the move isn't valid"
    (is (= {1 "O"} (game-controller/human-move {1 "O"} 1 2))))
  (testing "returns the same board if the move or players are nil"
    (is (= {1 "O"} (game-controller/human-move {1 "O"} nil 2)))
    (is (= {1 "O"} (game-controller/human-move {1 "O"} 1 nil)))))

(deftest get-human-move-test
  (with-redefs [game-controller/human-move (fn [_ _ _] "human-move")
                game-controller/ai-move (fn [_ _ _] "ai-move")]
  (testing "uses human-move if there are 2 players"
    (is (= "human-move" (game-controller/get-next-board nil nil 2))))
  (testing "uses human-move if it is the first move in a 1 player game with an empty board"
    (is (= "human-move" (game-controller/get-next-board nil nil 1))))
  (testing "uses ai-move if there is 1 player and it is not the first move"
    (is (= "ai-move" (game-controller/get-next-board {1 "X"} nil 1))))))
