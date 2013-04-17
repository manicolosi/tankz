(ns tankz.core)

(def colors ["orange" "blue" "red" "green" "yellow" "magenta" "cyan"])

(defn request-frame [callback]
  ((or (.-requestAnimationFrame js/window)
      (.-webkitRequestAnimationFrame js/window)
      (.-mozRequestAnimationFrame js/window)
      (.-oRequestAnimationFrame js/window)
      (.-msRequestAnimationFrame js/window)
      (fn [callback] (.setTimeout js/window callback (/ 1000 60))))
     callback))

(defn animate []
  (let [canvas (.getElementById js/document "game")
        context (.getContext canvas "2d")]
    (set! (.-fillStyle context) (rand-nth colors))
    (.fillRect context 0 0 (.-width canvas) (.-height canvas))
    (request-frame animate)))

(defn ^:export init []
  (if (and js/document
           (.-getElementById js/document))
    (animate)))
