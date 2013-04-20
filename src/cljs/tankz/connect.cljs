(ns tankz.connect
  (:require [clojure.browser.repl :as repl]))

;; Connect with `lein trampoline cljsbuild repl-listen`
(repl/connect "http://localhost:9000/repl")

;; Run after `lein repl` to connect with nREPL
(comment
  (require 'cljs.repl.browser)
  (cemerick.piggieback/cljs-repl
    :repl-env (doto (cljs.repl.browser/repl-env :port 9000) cljs.repl/-setup)))
