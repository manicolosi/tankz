(ns tankz.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]))

(defroutes app-routes
  ;(GET "/" [] "<p>Hello from Compojure</p>")
  ;(GET "/" [] (resp/redirect "/index.html"))
  (route/resources "/")
  (route/not-found "Page not found"))

(defn- wrap-dir-index [handler]
  (fn [request]
    (handler
     (let [k (if (contains? request :path-info) :path-info :uri) v (get request k)]
       (if (re-find #"/$" v)
         (assoc request k (format "%sindex.html" v))
         request)))))

(def app
  (-> (handler/site app-routes)
      (wrap-dir-index)))
