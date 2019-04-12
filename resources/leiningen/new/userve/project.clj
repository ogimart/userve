(defproject {{name}} "0.1.0"
  :description "{{name}} service"
  :url "http://fixme.com"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [;; clojure
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/core.async "0.4.490"]
                 ;; component
                 [com.stuartsierra/component "0.4.0"]
                 [com.walmartlabs/schematic "1.2.0"]
                 [com.walmartlabs/dyn-edn "0.2.0"]
                 ;; http/ws
                 [org.immutant/web "2.1.10"
                  :exclusions [ch.qos.logback/logback-classic]]
                 [ring/ring-core "1.7.1"]
                 [bidi "2.1.5"]
                 ;; message queue & cache
                 [com.taoensso/carmine "2.19.1"]
                 ;; serializers
                 [cheshire "5.8.1"]
                 ;; sql database
                 [com.h2database/h2 "1.4.198"]
                 [org.clojure/java.jdbc "0.7.9"]
                 [hikari-cp "2.7.0"]
                 ;; logging
                 [org.clojure/tools.logging "0.4.1"]
                 [ch.qos.logback/logback-classic "1.2.3"
                  :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.26"]
                 [org.slf4j/jcl-over-slf4j "1.7.26"]
                 [org.slf4j/log4j-over-slf4j "1.7.26"]
                 ;; util
                 [com.brunobonacci/safely "0.3.0"]
                 [clj-time "0.15.0"]
                 [environ "1.1.0"]]

  :source-paths ["src/clj"]
  :java-source-paths ["src/java"]
  :javac-options     ["-target" "1.8" "-source" "1.8"]
  :main {{name}}.main
  :profiles {:dev {:dependencies [[criterium "0.4.4"]
                                  [reloaded.repl "0.2.4"]
                                  [ring/ring-devel "1.7.1"]]
                   :source-paths ["dev"]
                   :repl-options {:init-ns user}}
             :uberjar {:aot :all}})
