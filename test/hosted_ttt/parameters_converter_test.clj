(ns hosted-ttt.parameters-converter-test
  (:require [clojure.test :refer :all]
            [hosted-ttt.parameters-converter :as converter]))

(deftest get-board-test
  (testing "return empty board when there is no board parameter"
    (is (= {} (converter/get-board "test=test"))))
  (testing "return empty board if there are no parameters"
    (is (= {} (converter/get-board ""))))
  (testing "returns empty board as board value if it's empty"
    (is (= {} (converter/get-board "board=&players=1&move=9"))))
  (testing "return hashmap version of the board string"
    (is (= {1 "X" 5 "O"} (converter/get-board "board=1X5O")))))

(deftest board-to-string-test
  (testing "return empty string when there is no board"
    (is (= "" (converter/board-to-string {}))))
  (testing "return string representation of a board hashmap"
    (is (= "1X5O" (converter/board-to-string {1 "X" 5 "O"})))))

(deftest get-move-test
  (testing "return nil when there is no move parameter"
    (is (nil? (converter/get-move-parameter "test=test"))))
  (testing "gets move value when move is one of many values"
    (is (= 9 (converter/get-move-parameter "board=test&players=1&move=9")))))

(deftest get-players-count-test
  (testing "return nil when there is no players parameter"
    (is (nil? (converter/get-players-parameter "test=test"))))
  (testing "gets players value when players is one of many values"
    (is (= 1 (converter/get-players-parameter "board=test&players=1&move=9")))))
