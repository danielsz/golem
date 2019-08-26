(ns golem.sipser)

;; finite automaton Sipser p.35

;; Q is a set of states #{S0 S1 S2}
;; Sigma alphabet {0 1}
;; delta transition function delta: Q x Sigma -> Q
;; q0 start state, q0 = S0
;; F accept states {S0}
;;     0  1
;; S0 S0  S1
;; S1 S2  S0
;; S2 S1  S2

;; (0 1 1 0 1) => accept / reject


