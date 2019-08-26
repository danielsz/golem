(ns golem.domain
  (:require [clojure.spec.alpha :as s]))

(defn validate [spec val]
  (let [v (s/conform spec val)]
    (if (= v ::s/invalid)
      (throw (ex-info "Invalid input" (s/explain-data spec val)))
      v)))

(s/def ::valid-when (s/coll-of fn?))
(s/def ::side-effect fn?)
(s/def ::next-state (s/or :keyword keyword? :nil nil?))
(s/def ::state-row (s/keys :req-un [::valid-when ::side-effect ::next-state]))
(s/def ::state-rows (s/coll-of ::state-row))
(s/def ::state-table (s/every-kv keyword? ::state-rows))
