(ns bot)

(defn log
    [arg]
    (.println *err* (pr-str arg))
    arg)

(defn send-command
    [command]
    (println command))

(defn parse
    [state line]
    ; (bot/log line)
    (if (or (empty? line) (= \# (first line)))
        state
        (let [parts            (clojure.string/split line #" ")
              [[handler args]] (for [s    [2 1]
                                    :let  [name   (clojure.string/replace (clojure.string/join "_" (take s parts)) "/" "_")
                                          handler (find-var (symbol (str "handlers/" name)))
                                          args    (drop s parts)]
                                    :when handler]
                                    [handler args])]
            (if (nil? handler)
                (throw (Exception. (str "Don't recognize: " line)))
                ; (do (prn handler args)
                (apply handler (cons state args)))))) ;)

(defn -main
    [] 
    (reduce parse {:round 0} (line-seq (java.io.BufferedReader. *in*))))
