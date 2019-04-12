(ns {{name}}.util.middleware
  (:require [clojure.tools.logging :as log]))

(def cors-headers
  {"Access-Control-Allow-Origin" "*"
   "Access-Control-Allow-Headers" "Content-Type"
   "Access-Control-Allow-Methods" "GET" })

(defn wrap-cors
  "allow requests from all origins"
  [handler]
  (fn [request]
    (let [response (handler request)]
      (update-in response [:headers] merge cors-headers))))

(defn wrap-comp
  "wrap request with components"
  [handler components]
  (fn [request]
    (handler (assoc request :comp components))))

(defn wrap-exception
  "wrap handler exceptions and return 500"
  [handler]
  (fn [request]
    (try
      (handler request)
      (catch Exception e
        (log/error (.getMessage e))
        (:status 500 :body "Internal server error")))))
