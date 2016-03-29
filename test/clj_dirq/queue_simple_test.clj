(ns clj-dirq.queue-simple-test
  (:refer-clojure :exclude [count get remove])
  (:require [clojure.test :refer :all]
            [clj-dirq.queue-simple :refer :all]
            [me.raynes.fs :as fs]))

(defn abs-path [f] (.getAbsolutePath f))

(def qpath (abs-path (fs/temp-dir "queue-")))

(defn write-file [data]
  (let [fpath (abs-path (fs/temp-file "element-"))]
    (spit fpath data)
    fpath))

(defn queue-path-fixture
  [f]
  (init! qpath)
  (f)
  (fs/delete-dir qpath))

(use-fixtures :each queue-path-fixture)

(deftest test-queue-path
  (is (= qpath (queue-path)))
  (is (fs/exists? (queue-path)))
  (is (fs/directory? (queue-path))))

(deftest test-id
  (is (string? (id))))

(deftest test-add-data
  (is (string? (add "")))
  (is (string? (add "foo")))
  (is (= "bar" (let [el (add "bar")]
                 (slurp (fs/file qpath el))))))

(deftest test-add-path
  (is (= "deleted" (let [fpath (write-file "deleted")
                         el    (add-path fpath)]
                     (is (not (fs/exists? fpath)))
                     (slurp (fs/file qpath el))))))

(deftest test-add
  (is (let [el (add "")]
        (= (abs-path (fs/file qpath (str el locked-suffix))) (path el)))))

(deftest test-lock-unlock
  (let [el (add "")]
    (is (true? (lock el)))
    (is (false? (lock el true)))
    (is (fs/exists? (path el)))
    (is (true? (unlock el)))
    (is (false? (unlock el true)))))

