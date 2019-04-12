(ns {{name}}.main
  (:gen-class)
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [com.stuartsierra.component :as component]
            [com.walmartlabs.dyn-edn :as dyn]
            [com.walmartlabs.schematic :as sc]
            [environ.core :refer [env]]))

(defn create-system
  "create system from a config edn"
  [& [config-file]]
  (->> (or config-file "config.default.edn")
       io/resource
       slurp
       (edn/read-string {:renders (dyn/env-readers)})
       (sc/assemble-system)))

(defn start-service
  "start service"
  [system]
  (component/start-system system))

(defn stop-service
  "stop service"
  [system]
  (component/stop-system system))

(defn add-shutdown-hook
  [system]
  (.addShutdownHook
   (Runtime/getRuntime)
   (Thread. #(stop-service system))))

(defn -main
  "service main entry point"
  [& args]
  (let [config-file (env :{{name}}-config)
        system (create-system config-file)]
    (add-shutdown-hook system)
    (start-service system)))
