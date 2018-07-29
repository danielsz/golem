(ns golem.core
  (:require [lang-utils.core :refer [seek]]))

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
    (if state
      (do
        (doseq [f (:actions state)]
          (reset! (:acc sm) (f @(:acc sm)))) ;maybe juxt
        (reset! (:state sm) (:next-state state)))
      @(:acc sm))))

(#_
 (if (:target state)
   @(:acc sm)
   (recur sm (:next-state state))))
