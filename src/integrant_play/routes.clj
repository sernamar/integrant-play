(ns integrant-play.routes
  (:require [integrant-play.db :refer [select-all select-by-name]]
            [compojure.core :refer [routes context GET]]
            [compojure.route :as route]
            [ring.util.response :refer [response not-found]]))

(defn db-routes [db]
  (context "/db" []
   (GET "/" [] (response (select-all db)))
   (GET "/:name" [name] (response (select-by-name db name)))))

(defn other-routes []
  (routes
   (GET "/" [] (response "Hello, World!"))
   (route/not-found (not-found "Not Found"))))

(defn all-routes [db]
  (routes
   (db-routes db)
   (other-routes)))
