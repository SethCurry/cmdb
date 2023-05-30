(ns cmdb.util-test
  (:require [clojure.test :refer :all]
            [cmdb.util :as util]))

(deftest in?
  (testing "Is in"
    (is (util/in? ["one" "two" "three"] "two")))
  (testing "not in"
    (is (not (util/in? ["one" "two" "three"] "four")))))