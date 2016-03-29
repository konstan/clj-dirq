(ns clj-dirq.queue-null
  "
  Null queue system providing the same interface as the other directory queue
  implementations.  The queue behaves like a black hole: added data disappears
  immediately so the queue therefore always appear empty.

  It can be used for testing purposes or to discard data like one would do on
  Unix by redirecting output to `/dev/null`.

  Please refer to `clj-dirq.queue` for general information about directory based
  queues.

  All the methods that add data -- return an invalid element name.

  All the methods that work on elements -- throw an `UnsupportedOperationException`
  exception.

  Author: Konstantin Skaburskas
  "
  (:refer-clojure :exclude [count get remove])
  (import (ch.cern.dirq QueueNull)))

(def ^:dynamic *queue-null* (QueueNull.))

(defn init!
  []
  (alter-var-root #'*queue-null* (constantly (QueueNull.))))

(defn queue-path
  []
  (.getQueuePath *queue-null*))

(defn id
  []
  (.getId *queue-null*))

(defn add
  [^String data]
  (.add *queue-null* data))

(defn add-path
  [path]
  (.addPath *queue-null* path))

(defn get
  [name]
  (.get *queue-null* name))

(defn get-as-byte-array
  [^String name]
  (.getAsByteArray *queue-null* name))

(defn path
  [path]
  (.getPath *queue-null* path))

(defn lock
  ([name]
   (lock name true))
  ([name permissive]
   (.lock *queue-null* name permissive)))

(defn unlock
  ([name]
   (.unlock *queue-null* name))
  ([name permissive]
   (.unlock *queue-null* name permissive)))

(defn remove [name]
  (.remove *queue-null* name))

(defn count
  []
  (.count *queue-null*))

(defn purge
  ([]
   (.purge *queue-null*))
  ([max-lock]
   (.purge *queue-null* max-lock))
  ([max-lock max-temp]
   (.purge *queue-null* max-lock max-temp)))

