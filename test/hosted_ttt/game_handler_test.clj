(ns hosted-ttt.game-handler-test
  (:require [clojure.test :refer :all]
            [hosted-ttt.parameters-converter :as converter]
            [hosted-ttt.game-controller :as game-controller]
            [hosted-ttt.game-handler :refer :all])
  (:import (javaserver.handlers Response Status)
           (javaserver Request)))

(def test-string "test")
(def empty-request (Request.))

(deftest create-response-test
  (with-redefs [converter/get-board (fn [_] test-string)
                converter/get-move-parameter (fn [_] test-string)
                converter/get-players-parameter (fn [_] test-string)
                game-controller/get-view (fn [board move players] (str board move players))]
    (testing "create-response always returns a response object"
      (is (instance? Response (create-response empty-request))))
    (testing "uses parameter-converter and html-builder to create the response body"
      (is (= (str test-string test-string test-string) (.getBody (create-response empty-request)))))))
