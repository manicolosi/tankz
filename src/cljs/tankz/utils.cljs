(ns tankz.utils
  (:require [clojure.string :as str]))

(defn log [& xs]
  (.log js/console (str/join " " xs)))
