(ns runner
  (:require [bot :refer :all]
            [brain]
            [handlers]))

(reduce (fn [state line]
  (let [next-state (parse state line)]
    (bot/log (str "Current state " (pr-str next-state)))
    next-state))
  {:round 0}
  (line-seq (java.io.BufferedReader. *in*))))