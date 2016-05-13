(ns hosted-ttt.game-view
  (:require [hosted-ttt.parameters-converter :as converter]
            [clojure-ttt.board :as board]))

(def header
  "<!DOCTYPE html>
  <html lang=\"en-US\">
  <div>")

(def close-form-div
  "</form></dive>")

(def tie-html
  "That's a tie game.
  <form action=\"/game\" method=\"get\">
    <input type=\"submit\" value=\"New Game?\">
  </form>")

(defn form-start-html [board players]
  (str
    "<form action=\"/game\" method=\"post\">
       <input type=\"hidden\" name=\"board\" value=\"" (converter/board-to-string board) "\"/>
       <input type=\"hidden\" name=\"players\" value=\"" players "\"/>"))

(defn space-html [board move]
  (let [piece (get board move)]
    (if piece
      (str "<td style=\"text-align:center\">" piece "</td>")
      (str "<td><input type=\"submit\" name=\"move\" value=\"" move "\"></td>"))))

(defn board-html [board]
  (str
    "<table>
       <tr>"
         (space-html board 1)
         (space-html board 2)
         (space-html board 3)
       "</tr>
        <tr>"
         (space-html board 4)
         (space-html board 5)
         (space-html board 6)
       "</tr>
        <tr>"
         (space-html board 7)
         (space-html board 8)
         (space-html board 9)
       "</tr>
     </table>"))

(defn- winner-message-html [board move players]
  (if (and (= "X" (board/winner board)) (= 1 players))
    "You cheated"
    (str "Good job " (board/winner board) "!"
         "<form action=\"/game\" method=\"get\">
            <input type=\"submit\" value=\"New Game?\">
          </form>")))

(defn build-view [board move players]
  (let [form-start-html (form-start-html board players)
        board-html (board-html board)]
    (if-let [winner (board/winner board)]
      (str header (winner-message-html board move players))
      (if (board/game-ended? board)
        (str header tie-html)
        (str header form-start-html board-html close-form-div)))))
