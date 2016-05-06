(ns hosted-ttt.game-handler
  (:require [hosted-ttt.parameters-converter :as converter]
            [hosted-ttt.html-builder :as html-builder])
  (:import (javaserver.handlers Handler Response Status)
           (javaserver Request)))

(defn create-response [request]
  (println (.toString request))
  (let [params (.getParameters request)
        version "HTTP/1.1"
        status Status/OK
        headers ""
        board (converter/get-board params)
        move (converter/get-move params)
        body (html-builder/build-html board move)]
    (println params)
    (Response. version status headers body)))

(defn handle-request []
  (reify Handler
    (handleRequest [_ request]
      (create-response request))))
