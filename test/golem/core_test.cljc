(ns golem.core-test
  (:require [golem.core :refer [state-machine update-state]]
            #?(:clj [clojure.test :as t :refer [deftest is]]
               :cljs [cljs.test :as t :include-macros true])))

(deftest sanity
  (is (= 2 (+ 1 1))))

(def find-lisp
  {:start [{:conditions [#(= \l (first %))] :actions [#(rest %)] :next-state :found-l}
           {:conditions [] :actions [#(rest %)] :next-state :start}]
   :found-l [{:conditions [#(= \i (first %))] :actions [#(rest %)] :next-state :found-i}
             {:conditions [#(= \l (first %))] :actions [#(rest %)] :next-state :found-l}
             {:conditions [] :actions [#(rest %)] :next-state :start}]
   :found-i [{:conditions [#(= \s (first %))] :actions [#(rest %)] :next-state :found-s}
             {:conditions [#(= \l (first %))] :actions [#(rest %)] :next-state :found-l}
             {:conditions [] :actions [#(rest %)] :next-state :start}]
   :found-s [{:conditions [#(= \p (first %))] :actions [#(rest %) #(println "found lisp" %)] :next-state :start :target true}
             {:conditions [#(= \l (first %))] :actions [#(rest %)] :next-state :found-l}
             {:conditions [] :actions [#(rest %)] :next-state :start}]})
