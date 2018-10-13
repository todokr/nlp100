(ns nlp100.section2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

;; 10
(defn count-line [file-name]
  (count (line-seq (io/reader (io/resource file-name)))))

(count-line "hightemp.txt")

;; 11
(defn replace-tab2space [file-name]
  (-> (slurp (io/resource file-name))
      (clojure.string/replace #"\t" " ")
      (as-> x (spit (str "result.txt") x))))

(replace-tab2space "hightemp.txt")
;; cat hightemp.txt | tr '\t' ' ' > result-sh.txt
;; diff -s result.txt result-sh.txt

;; 12
(defn split-column [file-name]
  (with-open [r (io/reader (io/resource file-name))
              col1-f (io/writer "col1.txt")
              col2-f (io/writer "col2.txt")]
    (loop [line (.readLine r)]
      (when line
        (let [[col1 col2 & _] (string/split line #"\t")]
          (do
            (.write col1-f (str col1 "\n"))
            (.write col2-f (str col2 "\n")))
          (recur (.readLine r)))))))

(split-column "hightemp.txt")
;; cut -f 1 hightemp.txt > col1-sh.txt
;; cut -f 2 hightemp.txt > col2-sh.txt
;; diff -s col1.txt col1-sh.txt
;; diff -s col2.txt col2-sh.txt

;; 13

(defn merge-columns [file-name1 file-name2]
  (with-open [f1 (io/reader file-name1)
              f2 (io/reader file-name2)
              result (io/writer "merged.txt")]
    (let [f1-lines (doall (line-seq f1))
          f2-lines (doall (line-seq f2))
          zipped (map vector f1-lines f2-lines)]
      (doseq [[c1 c2] zipped]
        (.write result (str c1 "\t" c2 "\n"))))))

(merge-columns "col1.txt" "col2.txt")
;; paste col1.txt col2.txt > merged-sh.txt
;; diff -s merged.txt merged-sh.txt

(defn head-lines [n file-name]
  (with-open [f (io/reader file-name)]
    (->> (line-seq f)
         (take n)
         (string/join "\n")
         (prn))))

(head-lines 4 "col1.txt")
