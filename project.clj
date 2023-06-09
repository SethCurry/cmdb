(defproject io.scurry/cmdb "0.0.2-SNAPSHOT"
  :description "Tools for building a CMDB"
  :url "https://github.com/SethCurry/cmdb"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [slingshot "0.12.2"]]
  :resource-paths ["resources/plantuml.jar"]
  :repl-options {:init-ns cmdb.core})
