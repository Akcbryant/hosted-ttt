(ns hosted-ttt.core
  (:require [clojure-ttt.core :as ttt]
            [hosted-ttt.game-handler :as game-handler])
  (:import (javaserver Authenticator Route Router Server)
           (javaserver.handlers RedirectHandler)))


(def ttt-port 5000)
(def ttt-directory "")

(def ttt-route (Route. "GET" "/game" (game-handler/handle-request)))
(def ttt-reroute (Route. "GET" "/" (RedirectHandler. "/game")))

(defn create-ttt-router []
  (let [tic-tac-toe-router (Router. ttt-directory)]
    (.addRoute tic-tac-toe-router ttt-route)
    (.addRoute tic-tac-toe-router ttt-reroute)
    tic-tac-toe-router))

(defn turn-on [server]
  (.turnOn server))

(defn -main []
  (let [router (create-ttt-router)
        authenticator (Authenticator.)
        server (Server. router authenticator ttt-port)]
    (turn-on server)))
