(ns integrant-play.routes
  (:require [integrant-play.db :refer [select-all select-by-name]]
            [compojure.core :refer [routes GET]]
            [compojure.route :as route]
            [ring.util.response :refer [response not-found]]))

(defn db-routes [db]
  (routes
   (GET "/" [] (response (select-all db)))
   (GET "/:name" [name] (response (select-by-name db name)))
   (route/not-found (not-found "Not Found"))))
