(ns service.core
  (:require [org.httpkit.server :as http]
            [service.router.core :refer [router]]))

(defn -main []
  (println "application starting")
  (http/run-server #'router {:port 8080}))
