(ns cmdb.plantuml-test
  (:require [clojure.test :refer :all]
            [cmdb.plantuml :as puml]))


(deftest quote-name
  (testing "Quoting a name without a space"
    (is (=
         (puml/quote-name (puml/->Component "no-space" {}))
         "no-space")))
  (testing "Quoting a name with a space"
    (is (=
         (puml/quote-name (puml/->Component "has space" {}))
         (str "\"has space\"")))))

(deftest link-label
  (testing "Normal getting link label"
    (is (= (puml/link-label (puml/->Link {} {} {:label "test-label"}))
       "test-label")))
  (testing "link has no label"
    (is (= (puml/link-label (puml/->Link {} {} {}))
           ""))))