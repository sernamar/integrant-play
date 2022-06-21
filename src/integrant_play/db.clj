(ns integrant-play.db
  (:require [next.jdbc :as jdbc]
            [integrant.core :as ig]))

(defn get-db [db-spec]
  (jdbc/get-datasource db-spec))

(defn select-by-name [db name]
  (jdbc/execute-one! db ["SELECT * FROM address WHERE name=?" name]))

(defn select-all [db]
  (jdbc/execute! db ["SELECT * FROM address"] ))

(defmethod ig/init-key :database/connection [_ {:keys [dbtype dbname]}]
  (get-db {:dbtype dbtype
           :dbname dbname}))
