(ns cmdb.html
  (:require [clojure.string :as string]))

(defprotocol HTML
  (to-html [this]))

(defrecord A
           [text href]
  HTML
  (to-html [this]
    (str "<a href=\"" (get this :href) "\">" (get this :text) "</a>")))
(def a ->A)

(defrecord Div
           [children]
  HTML
  (to-html [this]
    (str "<div>" (string/join "\n" (map to-html (get this :children))) "</div>")))
(def div ->Div)

(defrecord H1
           [text]
  HTML
  (to-html [this] (str "<h1>" (get this :text) "</h1>")))
(def h1 ->H1)

(defrecord Table [columns data]
  HTML
  (to-html [this] ""))
(def table ->Table)