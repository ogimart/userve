(ns {{name}}.routes.handler
  (:require [clojure.tools.logging :as log]
            [bidi.ring :refer [make-handler]]
            [ring.util.response :as res]
            [{{name}}.domain.db :as db]
            [{{name}}.monitor.health :as monitor]
            [{{name}}.util.middleware
             :refer [wrap-comp wrap-cors wrap-exception]]))

(defn root-handler
  "root handler"
  [request]
  (res/response "Http Server"))

(defn not-found-handler
  "error handler"
  [request]
  (res/not-found "Not found"))

(defn db-connections-handler
  "/monitor/db-connections handler"
  [{:keys [comp]}]
  (res/response (str "db connections: "
                     (monitor/db-connections (:db comp)))))

(defn redis-ping-handler
  "/monitor/redis-ping handler"
  [{:keys [comp]}]
  (res/response (str "redis ping: "
                     (monitor/redis-ping (:redis-conn comp)))))

(def routes ["/" [["" root-handler]
                  ["monitor/" [["db-connections" db-connections-handler]
                               ["redis-ping" redis-ping-handler]]]
                  [true not-found-handler]]])

(defn handler
  "app handler, wraps component"
  [app]
  (-> (make-handler routes)
      (wrap-comp app)
      (wrap-cors)))
