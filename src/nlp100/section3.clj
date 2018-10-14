(ns nlp100.section3
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]
            [clojure.string :as string]))

;; 20
(defn extract-uk [file-name]
  (with-open [f (io/reader (java.util.zip.GZIPInputStream.
                            (io/input-stream file-name)))]
    (->> (line-seq f)
         (map json/read-str)
         (filter (fn [{title "title"}] (re-matches #"イギリス" title)))
         (map #(get % "text"))
         (doall)
         (first))))

(spit "uk.txt" (extract-uk "./resources/jawiki-country.json.gz"))

;; 21
(defn extract-category [text]
  (string/join "\n" (re-seq #"(?m)^\[\[Category:.*?\]\]$" text)))

(extract-category (extract-uk "./resources/jawiki-country.json.gz"))
