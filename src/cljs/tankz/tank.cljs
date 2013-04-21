(ns tankz.tank
  (:require [tankz.canvas :as canvas]
            [tankz.input :as input]
            [tankz.utils :as utils]))

(defn draw-tank [tank context game]
  (let [[x y] (:position tank)]
    (canvas/draw-image context game :tank x y)))

(defn tank [x y]
  {:position [x y]})

(defn update-tank [tank]
  (let [[x y] (:position tank)]
    (cond
      (input/key-pressed? :up)    (assoc tank :position [x (dec y)])
      (input/key-pressed? :down)  (assoc tank :position [x (inc y)])
      (input/key-pressed? :left)  (assoc tank :position [(dec x) y])
      (input/key-pressed? :right) (assoc tank :position [(inc x) y])
      :else tank)))
