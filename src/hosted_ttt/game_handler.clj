(ns hosted-ttt.game-handler
  (:require [hosted-ttt.parameters-converter :as converter]
            [hosted-ttt.game-controller :as controller])
  (:import (javaserver.handlers Handler Response Status)
           (javaserver Request)))

(defn create-response [request]
  (println (.toString request))
  (let [request-body (.getBody request)
        version "HTTP/1.1"
        status Status/OK
        headers ""
        board (converter/get-board request-body)
        move (converter/get-move-parameter request-body)
        players (converter/get-players-parameter request-body)
        body (controller/get-view board move players)]
    (Response. version status headers body)))

(defn handle-request []
  (reify Handler
    (handleRequest [_ request]
      (create-response request))))
