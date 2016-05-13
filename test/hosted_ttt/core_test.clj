(ns hosted-ttt.core-test
  (:require [clojure.test :refer :all]
            [hosted-ttt.core :as core]
            [hosted-ttt.game-handler :as game-handler])
  (:import (javaserver Route Router)))

(deftest create-route
  (testing "Create a server with one route for /game"
    (let [ttt-router (core/create-ttt-router)]
      (is (true? (.hasUri ttt-router core/ttt-route)))
      (is (true? (.hasUri ttt-router core/ttt-reroute)))
      (is (true? (.hasUri ttt-router core/ttt-post-route))))))

(deftest turn-on-server
  (testing "main turns the server on"
    (with-redefs [core/turn-on (fn [_] true)]
      (is (true? (core/-main))))))

