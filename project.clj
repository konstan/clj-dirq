(defproject clj-dirq "0.1.0-SNAPSHOT"
  :description "Directory based queue."

  :url "https://github.com/konstan/clj-dirq"

  :author "Konstantin Skaburskas <konstantin.skaburskas@gmail.com>"

  :license {:name "Apache License, Version 2.0"
            :url  "http://www.apache.org/licenses/LICENSE-2.0"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ch.cern.dirq/dirq "1.7"]
                 [me.raynes/fs "1.4.6"]]

  :profiles {
             :test {:env          {:clj-env "test"}
                    :dependencies [[me.raynes/fs "1.4.6"]]}})

