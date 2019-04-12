(ns {{name}}.components.redis
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]))

(defrecord Redis [pool spec]
  component/Lifecycle
  (start [this]
    (log/info "loading redis config")
    ;; todo: ping redis, retry until connected
    this)
  (stop [this]
    this))

(defn new-redis
  "create Redis connections specs"
  [{:keys [redis-conn]}]
  (map->Redis {:pool (:pool redis-conn)
               :spec (:spec redis-conn)}))
