(ns tankz.tank
  (:require [tankz.canvas :as canvas]
            [tankz.input :as input]
            [tankz.utils :as utils]))

(defn draw-tank [tank context game]
  (let [[x y]      (:position tank)
        tank-rot   (:rotation tank)
        turret-rot (:turret tank)]
    ;; Optimize this after we start tracking FPS
    (canvas/save context)
    (canvas/translate context x y)
    (canvas/rotate context tank-rot)
    (canvas/draw-image context game :tank -24 -24)
    (canvas/restore context)
    (canvas/save context)
    (canvas/translate context x y)
    (canvas/rotate context turret-rot)
    (canvas/draw-image context game :turret -24 -24)
    (canvas/restore context)
    ))

(defn tank [x y]
  {:position [x y]
   :rotation 0})

(def movement-keys
  {:up   [2 -2]
   :down [-2  2]})

(def rotation-keys
  {:right 2
   :left -2})

(defn delta-rotation []
  (* (/ (* 2 Math/PI) 360)
     (reduce + (for [[k v] rotation-keys]
                 (if (input/key-pressed? k)
                  v
                  0)))))

(defn delta-position []
  (reduce (fn [[dx1 dy1] [dx2 dy2]] [(+ dx1 dx2) (+ dy1 dy2)])
          (for [[k v] movement-keys]
            (if (input/key-pressed? k)
              v
              [0 0]))))

(defn update-tank [tank]
  (let [rot     (:rotation tank)
        drot    (delta-rotation)
        nrot    (+ rot drot)
        [x y]   (:position tank)
        [dx dy] (delta-position)
        nx      (+ x (* dx (Math/sin nrot)))
        ny      (+ y (* dy (Math/cos nrot)))
        [mx my] (input/mouse-position)
        turret  (Math/atan2 (- x mx) (- my y))]
    (assoc tank :rotation nrot
                :position [nx ny]
                :turret   turret)))
