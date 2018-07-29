(ns golem.stack-test
  (:require [golem.stack :refer [state-machine update-state]]
            [clojure.test :refer [deftest is]]))

(def traffic-light
  {:green [{:valid-when [] :cb #(println "green") :next-state :yellow}]
   :yellow [{:valid-when [] :cb #(println "yellow") :next-state :red}]
   :red [{:valid-when [] :cb #(println "red") :next-state :green}]})

(deftest traffic-lights
  (let [foo (state-machine traffic-light :green)]
    (dotimes [_ 4]
      (update-state foo))
    (is (= (peek @(:state foo)) :yellow))))
