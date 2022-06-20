(ns integrant-play.db
  (:require [next.jdbc :as jdbc]))

(defn get-db [db-spec]
  (jdbc/get-datasource db-spec))

(defn select-by-name [db name]
  (jdbc/execute-one! db ["SELECT * FROM address WHERE name=?" name]))

(defn select-all [db]
  (jdbc/execute! db ["SELECT * FROM address"] ))
