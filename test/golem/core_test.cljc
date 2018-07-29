(ns golem.core-test
  (:require [golem.core :refer [state-machine update-state target-state]]
            #?(:clj [clojure.test :as t :refer [deftest is]]
               :cljs [cljs.test :as t :include-macros true])))

(def find-lisp
  {:start [{:conditions [#(= \l (first %))] :actions [#(rest %)] :next-state :found-l}
           {:conditions [] :actions [#(rest %)] :next-state :start}]
   :found-l [{:conditions [#(= \i (first %))] :actions [#(rest %)] :next-state :found-i}
             {:conditions [#(= \l (first %))] :actions [#(rest %)] :next-state :found-l}
             {:conditions [] :actions [#(rest %)] :next-state :start}]
   :found-i [{:conditions [#(= \s (first %))] :actions [#(rest %)] :next-state :found-s}
             {:conditions [#(= \l (first %))] :actions [#(rest %)] :next-state :found-l}
             {:conditions [] :actions [#(rest %)] :next-state :start}]
   :found-s [{:conditions [#(= \p (first %))] :actions [#(rest %) #(println "found lisp" %)] :next-state nil}
             {:conditions [#(= \l (first %))] :actions [#(rest %)] :next-state :found-l}
             {:conditions [] :actions [#(rest %)] :next-state :start}]})

(deftest find-lisp
  (let [foo (state-machine find-lisp :start "ablislasllllispsslispdddd")]
    (is (nil? (target-state foo)))))
