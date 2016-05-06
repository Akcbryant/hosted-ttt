(ns hosted-ttt.parameters-converter-test
  (:require [clojure.test :refer :all]
            [hosted-ttt.parameters-converter :as converter]))

(deftest get-board-test
  (testing "return nil when there is no board parameter"
    (is (nil? (converter/get-board "test =test"))))
  (testing "return value of the board parameter when it exists"
    (is (= "value" (converter/get-board "board =value"))))
  (testing "return nil if there are no parameters"
    (is (nil? (converter/get-board "")))))
