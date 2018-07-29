(ns golem.stack
  (:require
   [golem.domain :as domain :refer [validate]]
   [lang-utils.core :refer [seek]]))

(defn state-machine [table init-state]
  {:table (validate ::domain/state-table table)
   :state (atom (list init-state))})

(defn find-first [xs]
  (let [pred #(every? true? (map (fn [f] (f)) (:valid-when %)))]
    (seek pred xs)))

(defn update-state [sm]
  (let [key (peek @(:state sm))
        xs (get (:table sm) key)
        state (find-first xs)]
    (add-watch (:state sm) key (fn [k r os ns] ((:side-effect state))))
    (if state
      (do
        (swap! (:state sm) conj (:next-state state))
        (remove-watch (:state sm) key))
      sm)))
