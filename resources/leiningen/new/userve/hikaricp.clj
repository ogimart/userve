(ns {{name}}.components.hikaricp
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [hikari-cp.core :as hikari]))

(defrecord HikariCP [db-spec datasource]
  component/Lifecycle
  (start [this]
    (let [s (or datasource (hikari/make-datasource db-spec))]
      (assoc this :datasource s)))
  (stop [this]
    (when datasource
      (hikari/close-datasource datasource))
    (assoc this :datasource nil)))

(defn new-dbpool [{:keys [db-spec]}]
  (map->HikariCP {:db-spec db-spec}))
