(ns clojure-todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(defn welcome
  "A ring handler to process all requests sent to the webapprespond witha  simple welcome message"
  [request]
  ;; (if (= "/" (:uri request))
  ;;   {:status 200
  ;;    :body "<h1>Hello, Clojure World</h1>
  ;;           <p>Welcome to your first Clojure app.</p>
  ;;           <ul>
  ;;             <li>I update automatically</li>
  ;;             <li>I update automatically use defroutes to manage incoming requests</li>
  ;;           </ul>"
  ;;    :headers {}})
  (html [:h1 "Hello, Clojure World"]
        [:p "Welcome to your first Clojure app."]
        [:ul
         [:li "I update automatically"]
         [:li "I update automatically use defroutes to manage incoming requests"]
         [:li "I am deployed to Heroku"]
         [:li "I use hiccup as my HTML library"]]))

(defn goodbye
  "A song to wish you goodbye"
  [request]
  ;; {:status 200
  ;;  :body "<h1>Walking back to happiness</h1>
  ;;         <p>Walking back to happiness with you</p>
  ;;         <p>Said, Farewell to loneliness I knew</p>
  ;;         <p>Laid aside foolish pride</p>
  ;;         <p>Learnt the truth from tears I cried</p>"
  ;;  :headers {}}
  (html5 {:lang "en"}
         [:head (include-js "myscript.js") (include-css "mystyle.css")]
         [:body
          [:div ][:div [:h1 {:class "info"} "Walking back to happiness"]]
          [:div [:p "Walking back to happiness with you"]]
          [:div [:p "Said, Farewell to loneliness I knew"]]
          [:div [:p "Laid aside foolish pride"]]
          [:div [:p "Learnt the truth from tears I cried"]]]))

(defn about
  "Information about the website developer"
  [request]
  {:status 200
   :body "I am an awesome Clojure developer, well getting there..."
   :headers {}})

(defn request-info
  "View the information contained in the request, useful for debugging"
  [request]
  {:status 200
   :body (pr-str request)
   :headers {}})

(defn hello
  "A simple personalised greeting showing the use of variable path elements"
  [request]
  (let [name (get-in request [:route-params :name])]
    {:status 200
     :body (str "Hello " name ".  I got your name from the web URL")
     :headers {}}))

(def operands {"+" + "-" - "*" * ":" /})

(defn calculator
  "A very simple calculator that can add, divide, subtract and multiply.  This is done through the magic of variable path elements."
  [request]
  (let [a (Integer. (get-in request [:route-params :a]))
        b (Integer. (get-in request [:route-params :b]))
        op (get-in request [:route-params :op])
        f (get operands op)]
    (if f
      {:status 200
       :body (str "Calculated result: " (f a b))
       :headers {}}
      {:status 404
       :body "Sorry, unknown operator.  I only recognise + - * : (: is for division)"
       :headers {}})))

(defn trying-hiccup
  [request]
  (html5 {:lang "en"}
         [:head (include-js "myscript.js") (include-css "mystyle.css")]
         [:body
          [:div [:h1 {:class "info"} "This is Hiccup"]]
          [:div [:p "Take a look at the HTML generated in this page, compared to the about page"]]
          [:div [:p "Style-wise there is no difference between the pages as we havent added anything in the stylesheet, however the hiccup page generates a more complete page in terms of HTML"]]]))


(defroutes app
  (GET "/" [] welcome)
  (GET "/goodbye" [] goodbye)
  (GET "/about" [] about)
  ;; (GET "/request-info" [] request-info)
  (GET "/request-info" [] handle-dump)
  (GET "/hello/:name" [] hello)
  (GET "/calculator/:op/:a/:b" [] calculator)
  (GET "/trying-hiccup" [] trying-hiccup)
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
