(ns hosted-ttt.html-builder)

(defn build-html [board move]
  "<!DOCTYPE html>
  <html lang=\"en-US\">
  <form action=\"/game\" method=\"get\">
    <input type=\"hidden\" name=\"board\" value=\"test\"/>
    <input type=\"radio\" name=\"id\" value=\"1\"/>
    <input type=\"radio\" name=\"id\" value=\"2\"/>
    <input type=\"radio\" name=\"id\" value=\"3\"/>
    <input type=\"radio\" name=\"id\" value=\"4\"/>
    <input type=\"radio\" name=\"id\" value=\"5\"/>
    <input type=\"radio\" name=\"id\" value=\"6\"/>
    <input type=\"radio\" name=\"id\" value=\"7\"/>
    <input type=\"radio\" name=\"id\" value=\"8\"/>
    <input type=\"radio\" name=\"id\" value=\"9\"/>
    <button type=\"submit\"> Test </button>
  </form>")
