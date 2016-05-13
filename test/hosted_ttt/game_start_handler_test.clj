(ns hosted-ttt.game-start-handler-test
  (:require [clojure.test :refer :all]
            [hosted-ttt.game-start-handler :as game-start-handler])
  (:import (javaserver Request)
           (javaserver.handlers Response)))

(deftest create-response-test
  (testing "create-response returns the game-start-html"
    (is (= game-start-handler/game-start-html
           (.getBody (game-start-handler/create-response nil))))))
