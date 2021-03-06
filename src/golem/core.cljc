(ns golem.core
  (:require [lang-utils.core :refer [seek]]))

;; FSM with accumulator

(defn state-machine [table init-state acc]
  {:table table
   :state (atom init-state)
   :acc (atom acc)})

(defn find-first [xs acc]
  (let [pred #(every? true? (map (fn [f] (f acc)) (:conditions %)))]
    (seek pred xs)))

(defn update-state [sm]
  (let [xs (get (:table sm) @(:state sm))
        state (find-first xs @(:acc sm))]
    (do
      (doseq [f (:actions state)]
        (reset! (:acc sm) (f @(:acc sm)))) ;maybe juxt
      (when-let [next-state (:next-state state)]
        (reset! (:state sm) next-state)))))

(defn target-state [sm]
  (loop [x (update-state sm)]
    (when x (recur (update-state sm)))))
