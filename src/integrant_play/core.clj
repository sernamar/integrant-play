(ns integrant-play.core
  (:require [integrant-play.db :refer [get-db select-by-name select-all]])
  (:gen-class))

(def db (get-db {:dbtype "sqlite" :dbname "example.db"}))

(defn -main
  ([]
   (println (select-all db)))
  ([name]
   (println (select-by-name db name))))
