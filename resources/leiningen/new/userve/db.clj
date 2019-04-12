(ns {{name}}.domain.db
  (:require [clojure.java.jdbc :as jdbc]))

(defn h2-num-connections
  "Return number of active h2 db connections"
  [conn]
  (:connections
   (first
    (jdbc/query conn ["select count(*) as connections
                       from information_schema.sessions"]))))
