(ns clojure-todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]))

(defn welcome
  "A ring handler to process all requests sent to the webapprespond witha  simple welcome message"
  [request]
  (if (= "/" (:uri request))
    {:status 200
     :body "<h1>Hello, Clojure World</h1>
            <p>Welcome to your first Clojure app.</p>
            <ul>
              <li>I update automatically</li>
              <li>I update automatically use defroutes to manage incoming requests</li>
            </ul>"
     :headers {}}))

(defroutes app
  (GET "/" [] welcome)
  (not-found "<h1>This is not the page you are looking for</h1>
              <p>Sorry, the page you requested was not found!</p>"))

(defn -dev-main
  [port-number]
  (jetty/run-jetty (wrap-reload #'app)
                   {:port (Integer. port-number)}))

(defn -main
  [port-number]
  (jetty/run-jetty app
                   {:port (Integer. port-number)}))

(defn testlol
  []
  print "WTF")

(def port-number 8000)
