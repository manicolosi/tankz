(ns tankz.core
  (:require [tankz.input :as input]
            [tankz.utils :as utils]))

(def colors ["orange" "blue" "red" "green" "yellow" "magenta" "cyan"])

(defn load-img [name]
  (let [img (js/Image)
        src (str "/img/" name)]
    (utils/log "Loading:" name)
    (set! (.-src img) src)
    img))

(defn load-images []
  {:tank (load-img "tank.png")
   :turrent (load-img "turret.png")
   :sand (load-img "sand.png")})

(defn images-loaded? [images]
  (reduce (fn [acc [_ v]] (and acc (.-complete v)))
          images))

(defn draw-image [context game image x y]
  (.drawImage context (image (:images game)) x y))

(defn new-game []
  {:images (load-images)
   ; ...
   })

(def request-frame
  (or (.-requestAnimationFrame js/window)
      (.-webkitRequestAnimationFrame js/window)
      (.-mozRequestAnimationFrame js/window)
      (.-oRequestAnimationFrame js/window)
      (.-msRequestAnimationFrame js/window)
      (fn [callback] (.setTimeout js/window callback (/ 1000 60)))))

(defn draw-game [game]
  (let [canvas (.getElementById js/document "game")
        context (.getContext canvas "2d")]
    (doseq [x (range 0 (.-width canvas) 32)
          y (range 0 (.-height canvas) 32)]
      (draw-image context game :sand x y))))

(defn game-loop [game]
  ;; We really shouldn't do this on every game loop.
  (if (images-loaded? (:images game))
    (draw-game game)
    (utils/log "Not Ready"))
  (request-frame #(game-loop game)))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (input/listen js/window)
    (game-loop (new-game))))
