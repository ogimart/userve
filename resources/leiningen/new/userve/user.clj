(ns user)

(defn dev
  "load and swith to the dev namespace"
  []
  (require 'dev)
  (in-ns 'dev)
  :loaded)
