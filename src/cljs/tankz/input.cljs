(ns tankz.input
  (:require [tankz.utils :as utils]))

(def key-codes {37 :left 38 :up 39 :right 40 :down})
(def key-buffer (atom #{}))

(defn- key-down [event]
  (when-let [code (key-codes (.-keyCode event))]
    (swap! key-buffer conj (key-codes (.-keyCode event)))
    (comment (utils/log "Keys pressed:" @key-buffer))))

(defn- key-up [event]
  (when-let [code (key-codes (.-keyCode event))]
    (swap! key-buffer disj (key-codes (.-keyCode event)))
    (comment (utils/log "Keys pressed:" @key-buffer))))

(defn key-pressed? [key]
  (some #{key} key-buffer))

(def mouse-buffer (atom {}))

(defn- mouse-move [event]
  (swap! mouse-buffer assoc :position [(.-clientX event) (.-clientY event)])
  (comment (utils/log "Mouse:" @mouse-buffer)))

(defn- mouse-down [event]
  (swap! mouse-buffer assoc :click true)
  (comment (utils/log "Mouse:" @mouse-buffer)))

(defn- mouse-up [event]
  (swap! mouse-buffer assoc :click false)
  (comment (utils/log "Mouse:" @mouse-buffer)))

(defn mouse-clicked? []
  (:click @mouse-buffer))

(defn mouse-position []
  (:position @mouse-buffer))

(defn listen [obj]
  (doto obj
    (.addEventListener "keydown" key-down)
    (.addEventListener "keyup" key-up)
    (.addEventListener "mousedown" mouse-down)
    (.addEventListener "mouseup" mouse-up)
    (.addEventListener "mousemove" mouse-move)))
