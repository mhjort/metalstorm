(ns metalstorm.web
  (:use [compojure.route :only [resources not-found]])
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.middleware.stacktrace :as trace]
            [ring.middleware.session :as session]
            [ring.middleware.session.cookie :as cookie]
            [ring.util.json-response :as json-response]
            [ring.util.response :as response]
            [ring.adapter.jetty :as jetty]
            [cemerick.drawbridge :as drawbridge]
            [cheshire.core :as json]
            [environ.core :refer [env]]))

(def ^:private drawbridge
  (-> (drawbridge/ring-handler)
      (session/wrap-session)))

(defn- run-simulation [params]
  params)

(defroutes app
  (ANY "/repl" {:as req}
       (drawbridge req))
  (resources "/resources/")
  (GET "/*" [] (:body (response/resource-response "index.html" {:root "public"})))
  (POST "/api/runSimulation/" {body :body} (json-response/json-response (run-simulation (json/parse-string (slurp body) true))))
  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(defn wrap-error-page [handler]
  (fn [req]
    (try (handler req)
         (catch Exception e
           {:status 500
            :headers {"Content-Type" "text/html"}
            :body (slurp (io/resource "500.html"))}))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (-> #'app
                         ((if (env :production)
                            wrap-error-page
                            trace/wrap-stacktrace)))
                     {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
