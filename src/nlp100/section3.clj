(ns nlp100.section3
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]))

;; 20
(defn abount-brit [file-name]
  (with-open [f (io/reader (java.util.zip.GZIPInputStream.
                            (io/input-stream file-name)))]
    (->> (line-seq f)
         (map json/read-str)
         (filter (fn [{title "title"}] (re-matches #"イギリス" title)))
         (doall))))

(abount-brit "./resources/jawiki-country.json.gz")
