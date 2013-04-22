(ns tankz.tank
  (:require [tankz.canvas :as canvas]
            [tankz.input :as input]
            [tankz.utils :as utils]))

(defn draw-tank [tank context game]
  (let [r (:rotation tank)
        [x y] (:position tank)]
    (canvas/save context)
    (canvas/translate context x y)
    (canvas/rotate context r)
    (canvas/draw-image context game :tank -24 -24)
    (canvas/restore context)))

(defn tank [x y]
  {:position [x y]
   :rotation 0})

(def movement-keys
  {:up   -1
   :down  1})

(def rotation-keys
  {:right 1
   :left -1})

(defn delta-rotation []
  (* (/ (* 2 Math/PI) 360)
     (reduce + (for [[k v] rotation-keys]
                (if (input/key-pressed? k)
                  v
                  0)))))

(defn update-tank [tank]
  (assoc tank :rotation (+ (:rotation tank) (delta-rotation))))
