(ns clj-dirq.queue-simple
  "
  Simple queue system providing the same interface as the other directory queue
  implementations.

  Please refer to `clj-dirq.queue` for general information about directory based
  queues.

  TODO:
  - write documentation
  - write thread local binding for *queue-simple*

  Author: Konstantin Skaburskas <konstantin.skaburskas@gmail.com>

  "
  (:refer-clojure :exclude [count get remove])
  (import (ch.cern.dirq QueueSimple)))

(def ^:dynamic *queue-simple* nil)

(defn init!
  ([path]
   (alter-var-root #'*queue-simple* (constantly (QueueSimple. path))))
  ([path numask]
   (alter-var-root #'*queue-simple* (constantly (QueueSimple. path numask))))
  )

(def locked-suffix ".lck")

(defn queue-path
  []
  (.getQueuePath *queue-simple*))

(defn id
  []
  (.getId *queue-simple*))

(defn add
  [^String data]
  (.add *queue-simple* data))

(defn add-path
  [path]
  (.addPath *queue-simple* path))

;(defn get
;  [name]
;  (.get *queue-simple* name))
;
;(defn get-as-byte-array
;  [^String name]
;  (.getAsByteArray *queue-simple* name))

(defn path
  [path]
  (.getPath *queue-simple* path))

(defn lock
  ([name]
   (lock name true))
  ([name permissive]
   (.lock *queue-simple* name permissive)))

(defn unlock
  ([name]
   (.unlock *queue-simple* name))
  ([name permissive]
   (.unlock *queue-simple* name permissive)))

;(defn remove [name]
;  (.remove *queue-simple* name))
;
;(defn count
;  []
;  (.count *queue-simple*))
;
;(defn purge
;  ([]
;   (.purge *queue-simple*))
;  ([max-lock]
;   (.purge *queue-simple* max-lock))
;  ([max-lock max-temp]
;   (.purge *queue-simple* max-lock max-temp)))

