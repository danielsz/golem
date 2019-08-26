(ns golem.higher-order)

(defn fsm [s]
  (letfn [(init [s] (cond
                      (empty? s) (do (println "can't find \"lisp\"") false)
                      (= (first s) \l) (found-l (rest s))
                      :else (init (rest s))))
          (found-l [s] (cond
                         (empty? s) (do (println "can't find \"lisp\"") false)
                         (= (first s) \i) (found-i (rest s))
                         :else (init (rest s))))
          (found-i [s] (cond
                         (empty? s) (do (println "can't find \"lisp\"") false)
                         (= (first s) \s) (found-s (rest s))
                         :else (init (rest s))))
          (found-s [s] (cond
                         (empty? s) (do (println "can't find \"lisp\"") false)
                         (= (first s) \p) (found-p (rest s))
                         :else (init (rest s))))
          (found-p [s] (do (println "found \"lisp\"")
                           true))]
    (init s)))

;; with driver

(defn fsm-driver
  [s]
  (letfn [(init [c] (case c 
                      \l found-l
                      init))
          (found-l [c] (case c
                         \i found-i
                         init))
          (found-i [c] (case c
                         \s found-s
                         init))
          (found-s [c] (case c
                         \p found-p
                         init))
          (found-p [c] true)
          (driver [s state]
            (cond
              (empty? s) false
              (boolean? (state (first s))) (state (first s))
              :else (driver (rest s) (state (first s)))))]
    (driver s init)))
