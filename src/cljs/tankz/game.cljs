(ns tankz.game
  (:require [tankz.canvas :as canvas]
            [tankz.tank :as tank]
            [tankz.utils :as utils]))

(def ^:private game (atom {}))

(defn- load-img [name]
  (let [img (js/Image)
        src (str "/img/" name)]
    (utils/log "Loading:" name)
    (set! (.-src img) src)
    img))

;; TODO: Move back to core.cljs later and do this before the game is started.
(defn- load-images []
  {:tank (load-img "tank.png")
   :turrent (load-img "turret.png")
   :sand (load-img "sand.png")})

(defn- images-loaded? [images]
  (reduce (fn [acc [_ v]] (and acc (.-complete v)))
          images))

(defn- draw-game [game]
  (let [canvas (.getElementById js/document "game")
        context (.getContext canvas "2d")]
    (doseq [x (range 0 (.-width canvas) 32)
          y (range 0 (.-height canvas) 32)]
      (canvas/draw-image context game :sand x y))
    (tank/draw-tank (:tank game) context game)))

(defn- game-loop []
  ;; We really shouldn't do this on every game loop.
  (if (images-loaded? (:images @game))
    (draw-game @game)
    (utils/log "Not Ready"))
  (swap! game assoc :tank (tank/update-tank (:tank @game)))
  (canvas/request-frame game-loop))

(defn start-game []
  (reset! game {:images (load-images) :tank (tank/tank 400 300)})
  (game-loop))
