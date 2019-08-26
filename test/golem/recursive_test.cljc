(ns golem.recursive-test
  (:require [golem.recursive :refer [fsm]]
            #?(:clj [clojure.test :as t :refer [deftest is]]
               :cljs [cljs.test :as t :include-macros true])))

(deftest lisp-found
  (is (fsm "ablislasllllispsslispdddd")))

(deftest lisp-not-found
  (is (not (fsm "ablislasllllissslpdddd"))))

(deftest lisp-empty
  (is (not (fsm ""))))
