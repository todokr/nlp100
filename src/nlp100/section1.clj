(ns nlp100.section1)

;; 00
(clojure.string/reverse "stressed")

;; 01
(apply str
       (map
        second
        (filter
         (fn [[k v]] (odd? k))
         (map-indexed vector "パタトクカシーー"))))

;; 02
(interleave "パトカー" "タクシー")

;; 03
(map count
     (re-seq #"[a-zA-Z]+" ;; lazy sequence
             "Now I need a drink, alcoholic of course, after the heavy lectures involving quantum mechanics."))

;; 04
(def target-index [1 5 6 7 8 9 15 16 19])
(into
 {}
 (map
  (fn [[token idx]]
    (if (contains? target-index idx)
      (vector (apply str (take 1 token)) idx)
      (vector (apply str (take 2 token)) idx)))
  (map-indexed
   #(vector %2 (+ %1 1))
   (re-seq #"[a-zA-Z]+"
           "Hi He Lied Because Boron Could Not Oxidize Fluorine. New Nations Might Also Sign Peace Security Clause. Arthur King Can."))))
