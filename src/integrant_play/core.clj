(ns integrant-play.core
  (:require [integrant-play.db :refer [get-db]]
            [integrant-play.routes :refer [all-routes]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.adapter.jetty :as jetty]
            [integrant.core :as ig]
            [clojure.tools.logging :as log])
  (:gen-class))

(def config
  (ig/read-string (slurp "config.edn")))

(defmethod ig/init-key :database/connection [_ {:keys [dbtype dbname]}]
  (get-db {:dbtype dbtype
           :dbname dbname}))

(defmethod ig/init-key :server/handler [_ {:keys [db]}]
  (-> db
      all-routes
      (wrap-json-body {:keywords? true})
      wrap-json-response))

(defmethod ig/init-key :server/jetty [_ {:keys [handler port]}]
  (let [server (jetty/run-jetty handler {:port port})]
    (log/info "Server running on port" port)
    server))

(defn -main [& opts]
  (ig/init config))
