(ns tankz.core
  (:require [tankz.game :as game]
            [tankz.input :as input]
            [tankz.utils :as utils]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (input/listen js/window)
    (game/start-game)))
