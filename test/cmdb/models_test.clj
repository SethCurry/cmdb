(ns cmdb.models-test
  (:require [clojure.test :as t]
            [cmdb.models :as models]))

(models/defmodel Team [name])

(t/deftest defmodel-simple
  (t/testing "Creating a simple defmodel model"
    (do
      (create-Team "test-team")
      (t/is
       (and
        (= (get (first @coll-Team) :name) "test-team")
        (= (count @coll-Team) 1))))))