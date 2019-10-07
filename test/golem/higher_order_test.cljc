(ns golem.higher-order-test
  (:require [golem.higher-order :refer [fsm fsm-driver]]
            #?(:clj [clojure.test :as t :refer [deftest is]]
               :cljs [cljs.test :as t :include-macros true])))


(deftest lisp-found
  (is (fsm "ablislasllllispsslispdddd")))

(deftest lisp-not-found
  (is (not (fsm "ablislasllllissslpdddd"))))

(deftest lisp-empty
  (is (not (fsm ""))))

(deftest lisp-long
  (is (not (fsm "Takes a set of test/expr pairs. It evaluates each test one at a
time.  If a test returns logical true, cond evaluates and returns
the value of listhe corresponding expr and doesn't evaluate any of the
other tests or exprs. (cond) returns nil."))))

(deftest lisp-driver-found
  (is (fsm-driver "ablislasllllispsslispdddd")))
