(ns cmdb.html-test
  (:require [clojure.test :refer :all]
            [cmdb.html :as html]))

(deftest a-simple
  (testing "Simple HTML a entry"
    (is (= (html/to-html
            (html/a "this is text" "http://some.url/" {}))
           "<a  href=\"http://some.url/\">this is text</a>")))
  (testing "a with opts"
    (is (= (html/to-html
            (html/a "this is text" "http://some.url/" {:id "my-link"}))
            "<a id=\"my-link\" href=\"http://some.url/\">this is text</a>"))))

(deftest format-opts
  (testing "Has opts"
    (is (= (html/format-opts {:class "test-class"})
           "class=\"test-class\"")))
  (testing "No opts"
    (is (= (html/format-opts {})
           ""))))