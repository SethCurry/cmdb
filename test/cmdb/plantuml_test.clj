(ns cmdb.plantuml-test
  (:require [clojure.test :as t]
            [cmdb.plantuml :as puml]))


(t/deftest quote-name
  (t/testing "Quoting a name without a space"
    (t/is (=
         (puml/quote-name (puml/->Component "no-space" {}))
         "no-space")))
  (t/testing "Quoting a name with a space"
    (t/is (=
         (puml/quote-name (puml/->Component "has space" {}))
         (str "\"has space\"")))))

(t/deftest link-label
  (t/testing "Normal getting link label"
    (t/is (= (puml/link-label (puml/->Link {} {} {:label "test-label"}))
       "test-label")))
  (t/testing "link has no label"
    (t/is (= (puml/link-label (puml/->Link {} {} {}))
           ""))))

(t/deftest format-link-label
  (t/testing "Does not have link"
    (t/is (= (puml/format-link-label (puml/link {} {}))
           "")))
  (t/testing "Does have link"
    (t/is (= (puml/format-link-label (puml/link {} {} {:label "test-label"}))
           " : test-label"))))

(t/deftest Link-to-uml
  (t/testing "Does not have label"
    (t/is (= (puml/to-uml
            (puml/link
             (puml/->Component "test1" {})
             (puml/->Component "test2" {})))
           "[test1] --> [test2]")))
  (t/testing "Does have label"
    (t/is (= (puml/to-uml
            (puml/link
             (puml/->Component "test1" {})
             (puml/->Component "test2" {}) 
             {:label "test-label"}))
           "[test1] --> [test2] : test-label")))
  (t/testing "Has direction"
    (t/is (= (puml/to-uml
            (puml/link
             (puml/->Component "test1" {})
             (puml/->Component "test2" {})
             {:label "test-label" :direction "up"}))
           "[test1] -up-> [test2] : test-label"))))

(t/deftest is-valid-link-direction
  (t/testing "is valid"
    (t/is (= (puml/is-valid-link-direction? "up")
             true)))
  (t/testing "is not valid"
    (t/is (= (puml/is-valid-link-direction? "diagonal")
             false))))