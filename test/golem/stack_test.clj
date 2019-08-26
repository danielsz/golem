(ns golem.stack-test
  (:require [golem.stack :refer [state-machine update-state target-state]]
            [clojure.test :refer [deftest is]]))

(def traffic-light
  {:green [{:valid-when [] :side-effect #(println "green") :next-state :yellow}]
   :yellow [{:valid-when [] :side-effect #(println "yellow") :next-state :red}]
   :red [{:valid-when [] :side-effect #(println "red") :next-state :green}]})

(def traffic-light-with-target-state
  {:green [{:valid-when [] :side-effect #(println "green") :next-state :yellow}]
   :yellow [{:valid-when [] :side-effect #(println "yellow") :next-state :red}]
   :red [{:valid-when [] :side-effect #(println "red") :next-state :target}]
   :target [{:valid-when [] :side-effect #(println "done") :next-state nil}]})

(deftest traffic-lights
  (let [foo (state-machine traffic-light :green)]
    (dotimes [_ 4]
      (update-state foo))
    (is (= (peek @(:state foo)) :yellow))))

(deftest traffic-lights-with-target-state
  (let [foo (state-machine traffic-light-with-target-state :green)]
    (is (nil? (target-state foo)))))
