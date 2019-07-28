(ns clojure-todo-list.core
  (:require [ring.adapter.jetty :as jetty]))

;; (defn foo
;;   "I don't do a whole lot."
;;   [x]
;;   (println x "Hello, World!"))

;; (foo " Yo, ")

(defn welcome
  "A ring handler to process all requests sent to the webapp"
  [request]
  (if (= "/" (:uri request))
    {:status 200
     :body "<h1>Hello, Clojure World</h1>
            <p>Welcome to your first Clojure app.</p>"
     :headers {}}
    {:status 404
     :body "<h1>This is not the page you are looking for</h1>
            <p>Sorry, the page you requested was not found!></p>"
     :headers {}}))

(defn -main
  [port-number]
  (jetty/run-jetty welcome
     {:port (Integer. port-number)}))

(def port-number 8000)
