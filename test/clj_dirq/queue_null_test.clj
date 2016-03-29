(ns clj-dirq.queue-null-test
  (:refer-clojure :exclude [count get remove])
  (:require [clojure.test :refer :all]
            [clj-dirq.queue-null :refer :all]))

(deftest test-all
  (is (nil? (queue-path)))
  (is (= "NULL" (id)))
  (is (= "" (add "")))
  (is (= "" (add "foo")))
  (is (= "" (add-path "")))
  (is (= "" (add-path "foo")))
  (is (thrown? UnsupportedOperationException (get "")))
  (is (thrown? UnsupportedOperationException (get "foo")))
  (is (thrown? UnsupportedOperationException (get-as-byte-array "")))
  (is (thrown? UnsupportedOperationException (get-as-byte-array "foo")))
  (is (thrown? UnsupportedOperationException (path "")))
  (is (thrown? UnsupportedOperationException (path "foo")))
  (is (thrown? UnsupportedOperationException (lock "")))
  (is (thrown? UnsupportedOperationException (lock "foo" false)))
  (is (thrown? UnsupportedOperationException (unlock "")))
  (is (thrown? UnsupportedOperationException (unlock "foo" false)))
  (is (thrown? UnsupportedOperationException (remove "")))
  (is (thrown? UnsupportedOperationException (remove "foo")))
  (is (nil? (purge)))
  (is (nil? (purge 0)))
  (is (nil? (purge 0 0)))
  (is (= 0 (count))))
