(defproject clojure-todo-list "0.1.0-SNAPSHOT"
  :description "A todo list written in clojure"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring "1.7.1"]
                 [compojure "1.6.1"]]
  :repl-options {:init-ns clojure-todo-list.core}
  :main clojure-todo-list.core
  :min-lein-version "2.0.0"
  :uberjar-name "clojure-todo-list.jar"
  :profiles {:dev {:main clojure-todo-list.core/-dev-main}})
