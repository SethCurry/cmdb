(ns cmdb.html
  (:require [clojure.string :as string]))

(defprotocol HTML
  (to-html [this]))

(defn format-opts
  [opts]
  (string/join
   " "
   (map (fn [[k v]] (str (name k) "=\"" v "\"")) opts)))

(defrecord A
           [text href opts]
  HTML
  (to-html [this]
    (str "<a " (format-opts (get this :opts)) " href=\"" (get this :href) "\">" (get this :text) "</a>")))
(def a ->A)

(defrecord Div
           [children opts]
  HTML
  (to-html [this]
    (str "<div>" (string/join "\n" (map to-html (get this :children))) "</div>")))
(def div ->Div)

(defrecord H1
           [text opts]
  HTML
  (to-html [this] (str "<h1>" (get this :text) "</h1>")))
(def h1 ->H1)

(defrecord Table [columns data opts]
  HTML
  (to-html [this] ""))
(def table ->Table)