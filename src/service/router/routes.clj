(ns service.router.routes
  (:require [schema.core :as s]
            [service.scramble :refer [scramble?]]))

(defn health-check-handler [_]
  {:status 200
   :body {:ping "pong"}})

(defn post-scramble-handler
  [request]
  (let [{:keys [str1 str2]} (get-in request [:parameters :body])]
    {:status 200
     :body {:input {:str1 str1
                    :str2 str2}
            :result (scramble? str1 str2)}}))

(def routes
  ["/api"
   ["" {:get health-check-handler}]
   ["/scramble" {:post {:parameters {:body {:str1 s/Str
                                            :str2 s/Str}}
                        :handler post-scramble-handler}}]])
