(ns nlp100.section3
  (:require [clojure.java.io :as io]))

;; 20
(read-gzip )

(defn abount-brit [file-name]
  (with-open [f (io/reader (java.util.zip.GZIPInputStream.
                            (io/input-stream file-name)))]
    (prn (first (line-seq f)))))

(abount-brit "./resources/jawiki-country.json.gz")
