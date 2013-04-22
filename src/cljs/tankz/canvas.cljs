(ns tankz.canvas)

(defn draw-image [context game image x y]
  (.drawImage context (image (:images game)) x y))

(defn save [context]
  (.save context))

(defn restore [context]
  (.restore context))

(defn translate [context x y]
  (.translate context x y))

(defn rotate [context a]
  (.rotate context a))

(def request-frame
  (or (.-requestAnimationFrame js/window)
      (.-webkitRequestAnimationFrame js/window)
      (.-mozRequestAnimationFrame js/window)
      (.-oRequestAnimationFrame js/window)
      (.-msRequestAnimationFrame js/window)
      (fn [callback] (.setTimeout js/window callback (/ 1000 60)))))
