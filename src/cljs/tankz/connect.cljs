(ns tankz.connect
  (:require [clojure.browser.repl :as repl]))

;; To connect using `lein repl`:
;; user=> (require 'cljs.repl.browser)
;; user=> (cemerick.piggieback/cljs-repl
;;  #_=>   :repl-env (doto (cljs.repl.browser/repl-env :port 9000)
;;  #_=>   cljs.repl/-setup))

(repl/connect "http://localhost:9000/repl")
