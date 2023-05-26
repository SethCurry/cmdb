(ns cmdb.html-test
  (:require [clojure.test :refer :all]
            [cmdb.html :as html]))

(deftest a-simple
  (testing "Simple HTML a entry"
    (is (= (html/to-html (html/a "this is text" "http://some.url/")) "<a href=\"http://some.url/\">this is text</a>"))))