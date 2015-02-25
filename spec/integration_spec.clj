(ns integration-spec
  (:require [speclj.core :refer :all]
            [speclj.run.standard]
            [bot :refer :all]
            [brain]
            [handlers]))

(defn satisfy
    [actual expected]
    (case (first expected)
        \! (not (satisfy actual (subs expected 1 (dec (count expected)))))
        \[ (.contains actual (subs expected 1 (dec (count expected))))
        (= actual expected)))

(defn verify
    [game-name]
    (describe game-name
        (let [game  (slurp (str "spec/" game-name ".txt"))
              out   (java.io.ByteArrayOutputStream.)
              in    (java.io.ByteArrayInputStream. (.getBytes game))]
            (binding [*in*  (java.io.InputStreamReader. in)
                      *out* (java.io.OutputStreamWriter. out)]
                (bot/-main))
            (let [output   (String. (.toByteArray out))
                  actual   (last (clojure.string/split-lines output))
                  expected (->> (re-seq #"(?m)^# Valid: (.*)$" game) (map last))]
                (it (str "should output " (clojure.string/join " OR " expected) " actual: " actual)
                    (should (some (partial satisfy actual) expected)))))))

(describe "Sample game"
    (->> (file-seq (clojure.java.io/file "spec"))
      (map #(.getName %))
      (map (partial re-find #"^(.*).txt$"))
      (filter identity)
      (map last)
      (map verify))
    ; (verify "ASpecificTestThatYouWantToSingleOut")
)