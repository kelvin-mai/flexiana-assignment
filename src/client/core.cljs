(ns client.core
  (:require [reagent.dom :as rdom]
            [client.app :refer [app]]))

(defn ^:dev/after-load reload []
  (rdom/render [app]
               (.getElementById js/document "app"))
  (js/console.log "reloaded"))

(defn ^:export init []
  (js/console.log "application starting")
  #_(js/console.log "begin rendering")
  (reload))
