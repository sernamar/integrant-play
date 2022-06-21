(ns integrant-play.core
  (:require [integrant-play.db :refer [get-db]]
            [integrant-play.routes :refer [all-routes]]
            [ring.adapter.jetty :as jetty]
            [integrant.core :as ig]
            [clojure.tools.logging :as log])
  (:gen-class))

(def config
  (ig/read-string (slurp "config.edn")))

(defmethod ig/init-key :server/jetty [_ {:keys [handler port]}]
  (let [server (jetty/run-jetty handler {:port port})]
    (log/info "Server running on port" port)
    server))

(defn -main [& opts]
  (ig/init config))
