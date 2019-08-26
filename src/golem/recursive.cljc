(ns golem.recursive
  (:require [clojure.core.match :refer [match]]))

(defn fsm
  ([s state]
   (loop [s s
          state state]
     (if (empty? s)
       false
       (match [(first s) state]
              [\l :init] (recur (rest s) :found-l) 
              [_ :init] (recur (rest s) :init)
              [\i :found-l] (recur (rest s) :found-i)
              [_ :found-l] (recur (rest s) :init)
              [\s :found-i] (recur (rest s) :found-s)
              [_ :found-i] (recur (rest s) :init)
              [\p :found-s] (recur (rest s) :found-p)
              [_ :found-s] (recur (rest s) :init)
              [_ :found-p] true))))
  ([s]
   (fsm s :init)))
