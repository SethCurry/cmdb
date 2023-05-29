(ns cmdb.plantuml
  (:require [clojure.string :as string]
            [clojure.java.io :as io])
  (:import (net.sourceforge.plantuml SourceStringReader)))

(defprotocol PUMLObject
  (to-uml [this]))

(defn link-label
  [lnk]
  (let [lbl (get (get lnk :opts) :label)]
    (if (nil? lbl)
    ""
    lbl)))

(defn link-has-label?
  [lnk]
  (if (string/blank? (link-label lnk))
    false
    true))

(defn quote-name
  [obj]
  (if (string/includes? (get obj :name) " ")
    (str "\"" (get obj :name) "\"")
    (get obj :name)))

(defn format-link-label
  [lnk]
  (let [lbl (link-label lnk)]
    (if (nil? lbl)
      ""
      (str " : " lbl))))

(defrecord Link
           [from to opts]
  PUMLObject
  (to-uml [this] (str (to-uml (get this :from)) " -" (get (get this :opts) :direction) "-> " (to-uml (get this :to)) (format-link-label this))))

(defrecord Note [target text direction]
  PUMLObject
  (to-uml [this]
    (str "note " (get this :direction) " of " (quote-name this) "\n\t" (get this :text) "\nend note")))

(defrecord Component
           [name opts]
  PUMLObject
  (to-uml [this] (str "[" (get this :name) "]")))

(defrecord LinkOptions [direction])

(defn link
  ([from to] (->Link from to {}))
  ([from to opts] (->Link from to opts)))

(defrecord Cloud
           [name children]
  PUMLObject
  (to-uml [this]
    (str "cloud " (get this :name) " {\n" (string/join "\n" (map to-uml (get this :children))) "\n}")))

(defrecord Folder
           [name children]
  PUMLObject
  (to-uml [this]
    (str "folder " (get this :name) " {\n" (string/join "\n" (map to-uml (get this :children))) "\n}")))

(defrecord Graph [components links])

(defn png-from-text
  [txt output-file]
  (with-open [out (io/output-stream output-file)]
    (let [src-reader (SourceStringReader. txt)]
      (.outputImage src-reader out))))

(def style-sheet "!includeurl https://raw.githubusercontent.com/inthepocket/plantuml-styles/master/styles.plantuml")

(defn render-text
  [comps links]
  (let [comp-text (string/join "\n" (map to-uml comps)) link-text (string/join "\n" (map to-uml links))]
    (str "@startuml\n" style-sheet "\n" comp-text "\n" link-text "\n@enduml")))

(defn render-graph
  [g output-file]
  (png-from-text (render-text (get g :components) (get g :links)) output-file))