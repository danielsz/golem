(ns golem.stack-leaky
  (:require [lang-utils.core :refer [seek]]))

(defn state-machine [table init-state]
  {:table table
   :state (atom (list init-state))})

(defn find-first [xs]
  (let [pred #(every? true? (map (fn [f] (f)) (:conditions %)))]
    (seek pred xs)))

(defn update-state [sm]
  (let [key (peek @(:state sm))
        xs (get (:table sm) key)
        state (find-first xs)]
    (add-watch (:state sm) key (fn [k r os ns]
                                 (doseq [f (:actions state)]
                                   (f k r os ns)))) ; leaky abstraction, might be useful
    (if state
      (do
        (swap! (:state sm) conj (:next-state state))
        (remove-watch (:state sm) key))
      sm)))
