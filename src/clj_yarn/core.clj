(ns clj-yarn.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))


(def cli-options
  [[nil "--am-mem AM-MEM" "Application Master Memory"
    :parse-fn #(Integer/parseInt %)
    :validate [#(> % 0) "Must be a number greater than zero."]]
   [nil "--containers CONTAINERS" "Container Count"
    :parse-fn #(Integer/parseInt %)
    :validate [#(> % 0) "Must be a number greater than zero."]]
   [nil "--command CMD" "Command that runs inside the container"]])


(defn generate-cmd [curr-container cmd]
  ; Change how you'd like to command to look like
  (format cmd curr-container))

(defn generate-cmd-lst [container-cnt cmd]
  (let [containers (range container-cnt)]
    (map #(generate-cmd % cmd) containers)))

(defn -main
  [& args]
  (let [opts (parse-opts args cli-options)]
    (println (:options opts))
    (println (generate-cmd-lst 4 (-> opts :options :command)))))
