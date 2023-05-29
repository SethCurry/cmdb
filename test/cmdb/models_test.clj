(ns cmdb.models-test
  (:require [clojure.test :refer :all]
            [cmdb.models :as models]))

(deftest defmodel-simple
  (testing "Creating a simple defmodel model"
    (do
      (models/defmodel Team [name])
      (create-Team "test-team")
      (is
       (and
        (= (get (first @coll-Team) :name) "test-team")
        (= (count @coll-Team) 1))))))