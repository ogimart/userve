(ns leiningen.new.userve
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "userve"))

(defn userve
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' userve project.")
    (->files data
             ;; root files
             [".gitignore" (render ".gitignore" data)]
             [".hgignore" (render ".gitignore" data)]
             ["CHANGELOG.md" (render "CHANGELOG.md" data)]
             ["LICENSE" (render "LICENSE" data)]
             ["README.md" (render "README.md" data)]
             ["dev/dev.clj" (render "dev.clj" data)]
             ["dev/user.clj" (render "user.clj" data)]
             ["doc/intro.md" (render "intro.md" data)]
             ["project.clj" (render "project.clj" data)]
             ;; resources
             ["resources/config.default.edn" (render "config.default.edn" data)]
             ["resources/logback.xml" (render "logback.xml" data)]
             ;; src/clj
             ["src/clj/{{sanitized}}/main.clj" (render "main.clj" data)]
             ["src/clj/{{sanitized}}/components/hikaricp.clj" (render "hikaricp.clj" data)]
             ["src/clj/{{sanitized}}/components/immutant_web.clj" (render "immutant_web.clj" data)]
             ["src/clj/{{sanitized}}/components/redis.clj" (render "redis.clj" data)]
             ["src/clj/{{sanitized}}/domain/db.clj" (render "db.clj" data)]
             ["src/clj/{{sanitized}}/impl/implement.clj" (render "implement.clj" data)]
             ["src/clj/{{sanitized}}/monitor/health.clj" (render "health.clj" data)]
             ["src/clj/{{sanitized}}/routes/handler.clj" (render "handler.clj" data)]
             ["src/clj/{{sanitized}}/services/abstract.clj" (render "abstract.clj" data)]
             ["src/clj/{{sanitized}}/util/middleware.clj" (render "middleware.clj" data)]
             ;; test
             ["test/{{sanitized}}/main_test.clj" (render "main_test.clj" data)])))
