(ns hosted-ttt.core
  (:require [clojure-ttt.core :as ttt]
            [hosted-ttt.game-handler :as game-handler]
            [hosted-ttt.game-start-handler :as game-start-handler])
  (:import (javaserver Authenticator Route Router Server)
           (javaserver.handlers RedirectHandler)))


(def ttt-port 5000)
(def ttt-directory "")

(def ttt-reroute (Route. "GET" "/" (RedirectHandler. "/game")))
(def ttt-route (Route. "GET" "/game" (game-start-handler/handle-request)))
(def ttt-post-route (Route. "POST" "/game" (game-handler/handle-request)))

(defn create-ttt-router []
  (let [tic-tac-toe-router (Router. ttt-directory)]
    (.addRoute tic-tac-toe-router ttt-reroute)
    (.addRoute tic-tac-toe-router ttt-route)
    (.addRoute tic-tac-toe-router ttt-post-route)
    tic-tac-toe-router))

(defn turn-on [server]
  (.turnOn server))

(defn -main []
  (let [router (create-ttt-router)
        authenticator (Authenticator.)
        server (Server. router authenticator ttt-port)]
    (turn-on server)))
