(ns hosted-ttt.game-view-test
  (:require [clojure.test :refer :all]
            [hosted-ttt.game-view :as game-view]))

(def x-wins-board {1 "X" 2 "X" 3 "X"})

(def form-start-html "form-start-html")
(def board-html "board-html")
(def winner-message-html "winner-message-html")
(def empty-board {})
(def winner-board {1 "O" 2 "O" 3 "O"})
(def tie-board {1 "O" 2 "X" 3 "O"
                4 "X" 5 "X" 6 "O"
                7 "O" 8 "O" 9 "X"})

(deftest winner-message-test
  (with-redefs [game-view/form-start-html (fn [_ _] form-start-html)
                game-view/board-html (fn [_] board-html)
                game-view/winner-message-html (fn [_ _ _] winner-message-html)]
    (testing "if there is no winner build new view"
      (is (= (str game-view/header form-start-html board-html game-view/close-form-div)
             (game-view/build-view '{1 "X"} 2 2))))
    (testing "if there is a tie use a header and the tie-html"
      (is (= (str game-view/header game-view/tie-html)
             (game-view/build-view tie-board 3 2))))
    (testing "if there is a winner use winner-message-html"
      (is (= (str game-view/header winner-message-html)
             (game-view/build-view winner-board 3 2))))))

(deftest html-board-test
  (let [piece-html "<td style=\"text-align:center\">X</td>"]
    (testing "return a board with the letter X in the first spot when given {1 X}"
      (is (true? (.contains (game-view/board-html {1 "X"}) piece-html))))
    (testing "return a board with no letters and all spaces when given an empty board"
      (is (false? (.contains (game-view/board-html {}) piece-html))))))

(deftest winner-message-test
  (testing "return the cheating message if the human wins against the computer"
    (is (= game-view/cheating-message (game-view/winner-message-html x-wins-board 1))))
  (testing "return the new-game-html when someone wins that isn't cheating"
    (is (.contains (game-view/winner-message-html x-wins-board 2) game-view/new-game-html))))
