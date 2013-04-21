(ns tankz.tank
  (:require [tankz.canvas :as canvas]
            [tankz.input :as input]
            [tankz.utils :as utils]))

(defn draw-tank [tank context game]
  (let [[x y] (:position tank)]
    (canvas/draw-image context game :tank x y)))

(defn tank [x y]
  {:position [x y]})

(def keys-to-deltas
  {:up    [ 0 -1]
   :down  [ 0  1]
   :left  [-1  0]
   :right [ 1  0]})

(defn find-movement []
  (reduce (fn [[dx1 dy1] [dx2 dy2]] [(+ dx1 dx2) (+ dy1 dy2)])
    (for [[k v] keys-to-deltas]
      (if (input/key-pressed? k)
        v
        [0 0]))))

(defn update-tank [tank]
  (let [[x y] (:position tank)
        [dx dy] (find-movement)]
    (assoc tank :position [(+ x dx) (+ y dy)])))
