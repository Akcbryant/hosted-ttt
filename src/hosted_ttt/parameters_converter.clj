(ns hosted-ttt.parameters-converter
  (:require [clojure.string :as str]))

(defn- parse-params [params]
  (if (empty? params) nil
    (let [split-params (str/split params #"&")
          param-key-values (map #(str/split % #"=") split-params)
          padded-param-key-values (map #(if (= 1 (count %)) (conj % nil) %) param-key-values)]
      (into {} padded-param-key-values))))

(defn- get-value [params key-string]
  (if-let [value (get (parse-params params) key-string)]
    (read-string value)
    nil))

(defn- string-to-board [board-string]
  (if board-string
    (apply hash-map (map-indexed (fn [idx itm]
                                   (if (even? idx)
                                     (read-string itm) itm)) (str/split board-string #"")))
    {}))

(defn board-to-string [board]
  (if (empty? board) ""
    (str/join (flatten (into [] board)))))

(defn get-board [params]
  (string-to-board (get (parse-params params) "board")))

(defn get-move-parameter [params]
  (get-value params "move"))

(defn get-players-parameter [params]
  (get-value params "players"))
