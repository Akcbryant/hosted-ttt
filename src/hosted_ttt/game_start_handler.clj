(ns hosted-ttt.game-start-handler
  (:require [hosted-ttt.parameters-converter :as converter])
  (:import (javaserver.handlers Handler Response Status)
           (javaserver Request)))

(def game-start-html
  "<!DOCTYPE html>
  <html lang=\"en-US\">
  <form action=\"/game\" method=\"post\">
    <input type=\"radio\" name=\"players\" id=\"player1\" value=\"1\"/>
    <label for=\"player1\">1 Player</label>
    <input type=\"radio\" name=\"players\" id=\"player2\" value=\"2\"/>
    <label for=\"player2\">2 Players</label>
    <button type=\"submit\"> Choose </button>
  </form>")

(defn create-response[request]
  (let [version "HTTP/1.1"
        status Status/OK
        headers ""
        body game-start-html]
    (Response. version status headers body)))

(defn handle-request []
  (reify Handler
    (handleRequest [_ request]
      (create-response request))))
