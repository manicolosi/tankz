(defproject tankz "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.cemerick/piggieback "0.0.4"]
                 [compojure "1.1.5"]]

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]} 
  
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-ring "0.8.3"]]
  :cljsbuild {:builds [{:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/tankz.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  :ring {:handler tankz.core/app})
