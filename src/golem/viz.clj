(ns golem.viz
  (:require [fsmviz.core :as fsmviz]))


(defn translate-map [sm]
  (let [n 0
        xs (for [x sm]
             (partition 2 (interleave (repeat (key x)) (keep :next-state (val x)))))]
    (->> (reduce (fn [a b] (conj a (map (fn [x] (into [] (interpose (keyword (gensym)) x))) b))) '() xs)
         (reduce into []))))

(defn generate-image [xs name]
  (fsmviz/generate-image xs name))
