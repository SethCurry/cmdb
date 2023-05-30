(ns cmdb.util-test
  (:require [clojure.test :as t]
            [cmdb.util :as util]))

(t/deftest in?
  (t/testing "Is in"
    (t/is (util/in? ["one" "two" "three"] "two")))
  (t/testing "not in"
    (t/is (not (util/in? ["one" "two" "three"] "four")))))