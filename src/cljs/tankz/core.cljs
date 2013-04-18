(ns tankz.core)

(def colors ["orange" "blue" "red" "green" "yellow" "magenta" "cyan"])

(defn log [x & xs]
  (.log js/console (apply str x xs)))

(defn load-img [name]
  (let [img (js/Image)
        src (str "/img/" name)]
    (log "Loading:" name)
    (set! (.-src img) src)
    img))

(defn load-images []
  {:tank (load-img "tank.png")
   :turrent (load-img "turret.png")
   :sand (load-img "sand.png")})

(defn new-game []
  {:assets (load-images)
   ; ...
   })

(defn request-frame [callback]
  ((or (.-requestAnimationFrame js/window)
      (.-webkitRequestAnimationFrame js/window)
      (.-mozRequestAnimationFrame js/window)
      (.-oRequestAnimationFrame js/window)
      (.-msRequestAnimationFrame js/window)
      (fn [callback] (.setTimeout js/window callback (/ 1000 60))))
     callback))

(defn draw-game [game]
  (let [canvas (.getElementById js/document "game")
        context (.getContext canvas "2d")]
    (doseq [x (range 0 (.-width canvas) 32)
          y (range 0 (.-height canvas) 32)]
      (.drawImage context (:sand (:assets game)) x y))))

(defn game-loop [game]
  (if (reduce (fn [acc [_ v]] (and acc (.-complete v))) (:assets game))
    (draw-game game)
    (log "Not Ready"))
  (request-frame #(game-loop game)))

(defn ^:export init []
  (if (and js/document
           (.-getElementById js/document))
    (game-loop (new-game))))
