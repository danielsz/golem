(ns golem.stack-leaky-test
  (:require [golem.stack-leaky :refer [state-machine update-state]]
            [clojure.test :refer [deftest is]]))

(def traffic-light
  {:green [{:conditions [] :actions [(fn [_ _ _ _] (println "green"))] :next-state :yellow}]
   :yellow [{:conditions [] :actions [(fn [_ _ _ _] (println "yellow"))] :next-state :red}]
   :red [{:conditions [] :actions [(fn [_ _ _ _] (println "red"))] :next-state :green}]})

(deftest traffic-lights
  (let [foo (state-machine traffic-light :green)]
    (dotimes [_ 4]
      (update-state foo))
    (is (= (peek @(:state foo)) :yellow))))
