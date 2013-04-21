(ns tankz.utils
  (:require [clojure.string :as str]))

(defn log
  ([x]
   (.log js/console x)
   x)
  ([& xs]
   (.log js/console (str/join " " xs))))
