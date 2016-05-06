(ns hosted-ttt.parameters-converter
  (:require [clojure.string :as str]))

(defn- parse-params [params]
  (let [param-lines (str/split-lines params)
        param-key-values (map #(str/split % #"=") param-lines)]
    (if (zero? (count params)) nil
      (into {} param-key-values))))

(defn- get-value [params key-string]
  (get (parse-params params) key-string))

(defn get-board [params]
  (let [board-params (get-value params "board ")]
    board-params))

(defn get-move [params]
  (get-value params "move "))
