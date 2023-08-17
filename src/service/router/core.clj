(ns service.router.core
  (:require [muuntaja.core :as m]
            [reitit.ring :as ring]
            [reitit.coercion.schema]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.coercion :as coercion]
            [ring.middleware.cors :refer [wrap-cors]]
            [service.router.routes :refer [routes]]))

(def router
  (ring/ring-handler
   (ring/router
    [routes]
    {:data {:coercion reitit.coercion.schema/coercion
            :muuntaja m/instance
            :middleware [muuntaja/format-middleware
                         [wrap-cors :access-control-allow-origin [#"http://localhost:3000"]
                          :access-control-allow-methods [:get :post]]
                         coercion/coerce-exceptions-middleware
                         coercion/coerce-request-middleware
                         coercion/coerce-response-middleware]}})
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-default-handler))))
