(defproject metalstorm "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://metalstorm.herokuapp.com"
  :license {:name "FIXME: choose"
            :url "http://example.com/FIXME"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.1"]
                 [ring "1.1.8"]
                 [http-kit "2.1.16"]
                 [ring-json-response "0.2.0"]
                 [environ "0.2.1"]
                 [clj-gatling "0.0.4"]
                 [com.cemerick/drawbridge "0.0.6"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.2.1"]]
  :hooks [environ.leiningen.hooks]
  :profiles {:production {:env {:production true}}})
