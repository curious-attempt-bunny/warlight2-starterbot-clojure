(defproject warlight2 "0.0.1"
    :main runner
    :dependencies [ [org.clojure/clojure "1.6.0"]
                    [speclj "3.1.0"]]
    :min-lein-version "2.0.0"
    :profiles {:dev {:dependencies [[speclj "3.1.0"]]}}
    :plugins [[speclj "3.1.0"]] 
    :test-paths ["spec"])
