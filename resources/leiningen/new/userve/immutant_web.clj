(ns {{name}}.components.immutant-web
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [immutant.web :refer [run run-dmc stop]]))

(defrecord ImmutantWeb [handler options]
  component/Lifecycle
  (start [this]
    (log/info "starting http server")
    (let [dev-mode (:dev-mode options)
          opts (dissoc options :dev-mode)]
      (assoc this :server (if dev-mode
                            (run-dmc (handler this) opts)
                            (run (handler this) opts)))))
  (stop [this]
    (when-let [server (:server this)]
      (log/info "stopping http server")
      (assoc this :server (stop server))
      this)))

(defn new-httpserver
  "create http-server"
  [{:keys [handler options]}]
  (require (symbol (namespace handler)))
  (map->ImmutantWeb {:handler (resolve handler) :options options}))
