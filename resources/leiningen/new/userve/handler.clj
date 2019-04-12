(ns {{name}}.routes.handler
  (:require [clojure.tools.logging :as log]
            [bidi.ring :refer [make-handler]]
            [ring.util.response :as res]
            [{{name}}.domain.db :as db]
            [{{name}}.monitor.health :as monitor]
            [{{name}}.util.middleware
             :refer [wrap-comp wrap-cors wrap-exception]]))

(defn root-endpoint
  "root handler"
  [request]
  (res/response "Http Server"))

(defn not-found-endpoint
  "error handler"
  [request]
  (res/not-found "Not found"))

(defn db-connections-endpoint
  "/monitor/db-connections handler"
  [{:keys [comp]}]
  (res/response (str "db connections: "
                     (monitor/db-connections (:db comp)))))

(defn redis-ping-endpoint
  "/monitor/redis-ping handler"
  [{:keys [comp]}]
  (res/response (str "redis ping: "
                     (monitor/redis-ping (:redis-conn comp)))))

(def routes ["/" [["" root-endpoint]
                  ["monitor/" [["db-connections" db-connections-endpoint]
                               ["redis-ping" redis-ping-endpoint]]]
                  [true not-found-endpoint]]])

(defn handler
  "app handler, wraps component"
  [app]
  (-> (make-handler routes)
      (wrap-comp app)
      (wrap-cors)))
