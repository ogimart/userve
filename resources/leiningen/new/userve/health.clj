(ns {{name}}.monitor.health
  (:require [clojure.tools.logging :as log]
            [{{name}}.domain.db :as db]
            [taoensso.carmine :as car :refer [wcar]]))

(defn jvm-runtime
  "jvm runtime stats"
  []
  (let [rt (java.lang.Runtime/getRuntime)]
    {:available-processors (.availableProcessors rt)
     :thread-count (.getThreadCount
                     (java.lang.management.ManagementFactory/getThreadMXBean))
     :free-memory (.freeMemory rt)
     :total-memory (.totalMemory rt)
     :max-memory (.maxMemory rt)
     :unit-memory "bytes"}))

(defn redis-ping
  "ping redis connection"
  [conn]
  (car/wcar conn (car/ping)))

(defmulti db-connections
  "number of database connections"
  (fn [conn] (keyword (:adapter (:db-spec conn)))))

(defmethod db-connections :default [conn]
  (log/error "db adapter not implemented") 0)

(defmethod db-connections :h2 [conn]
  (try
    (db/h2-num-connections conn)
    (catch Throwable t
      (log/error t) 0)))








