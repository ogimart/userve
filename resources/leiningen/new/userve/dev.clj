(ns dev
  (:require [reloaded.repl :refer [system init start stop go reset reset-all]]
            [{{name}}.main :refer [create-system]]
            [clojure.java.jdbc :as j]
            [clojure.pprint :refer [pprint]]
            [taoensso.carmine :as car :refer [wcar]]))

(reloaded.repl/set-init! #(create-system))

(comment
  ;; for repl session:
  (go)
  (stop)
  (start)
  (reset)
  (reset-all)
  (pprint (into {} system))
  )
