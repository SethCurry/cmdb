(ns cmdb.html-test
  (:require [clojure.test :as t]
            [cmdb.html :as html]))

(t/deftest a-simple
  (t/testing "Simple HTML a entry"
    (t/is (= (html/to-html
            (html/a "this is text" "http://some.url/" {}))
           "<a  href=\"http://some.url/\">this is text</a>")))
  (t/testing "a with opts"
    (t/is (= (html/to-html
            (html/a "this is text" "http://some.url/" {:id "my-link" :style {:backgroundColor "red"}}))
            "<a id=\"my-link\" style=\"backgroundColor: red\" href=\"http://some.url/\">this is text</a>"))))

(t/deftest format-opts
  (t/testing "Has opts"
    (t/is (= (html/format-opts {:class "test-class"})
           "class=\"test-class\"")))
  (t/testing "No opts"
    (t/is (= (html/format-opts {})
           ""))))