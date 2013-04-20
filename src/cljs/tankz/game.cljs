(ns tankz.game
  (:require [tankz.canvas :as canvas]
            [tankz.utils :as utils]))

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
      (canvas/draw-image context game :sand x y))))

(defn- game-loop []
  ;; We really shouldn't do this on every game loop.
  (if (images-loaded? (:images @game))
    (draw-game @game)
    (utils/log "Not Ready"))
  (canvas/request-frame game-loop))

(def ^:private game (atom {}))

(defn start-game []
  (reset! game {:images (load-images)})
  (game-loop))
