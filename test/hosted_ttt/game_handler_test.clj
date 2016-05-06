(ns hosted-ttt.game-handler-test
  (:require [clojure.test :refer :all]
            [hosted-ttt.parameters-converter :as converter]
            [hosted-ttt.html-builder :as html-builder]
            [hosted-ttt.game-handler :refer :all])
  (:import (javaserver.handlers Response Status)
           (javaserver Request)))

(def test-string "test")
(def empty-request (Request.))

(deftest handle-request-is-called
  (testing "handle-request returns a Response"
    (is (instance? Response (.handleRequest (handle-request) (Request.))))))

(deftest create-response-test
  (testing "create-response always returns a response object"
    (is (instance? Response (create-response empty-request))))
  (testing "uses parameter-converter and html-builder to create the response body"
    (with-redefs [converter/get-board (fn [_] test-string)
                  converter/get-move (fn [_] test-string)
                  html-builder/build-html (fn [board move] (str board move))]
       (is (= (str test-string test-string) (.getBody (create-response empty-request)))))))
