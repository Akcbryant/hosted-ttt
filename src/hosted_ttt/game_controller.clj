(ns hosted-ttt.game-controller
  (:require [hosted-ttt.game-view :as game-view]
            [clojure-ttt.board :as board]
            [clojure-ttt.hard-ai :as ai]
            [clojure.string :as string]))

(defn valid-move? [board move players]
  (if (or (nil? move) (nil? players))
    false
    (board/valid-move? board move)))

(defn human-move [board move players]
  (if (valid-move? board move players)
    (let [current-player (board/whose-turn board)] 
      (board/make-move board move current-player))
      board))

(defn- ai-move [board move players]
    (let [next-board (human-move board move players)]
      (ai/move next-board (board/whose-turn next-board))))

(defn- first-move? [board move players]
  (and (empty? board) (nil? move) (= players 1)))

(defn get-next-board [board move players]
  (if (or (= 2 players) (first-move? board move players))
    (human-move board move players)
    (ai-move board move players)))

(defn get-view [board move players]
  (let [next-board (get-next-board board move players)]
    (game-view/build-view next-board move players)))
