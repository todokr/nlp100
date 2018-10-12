(ns nlp100.section1)

;; 00
(clojure.string/reverse "stressed")

;; 01
(apply str (take-nth 2 "パタトクカシーー"))

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

;; 05
(defn n-gram [n seq & [mode]]
  (map
   #(apply str %)
   (if (= mode :word)
     (partition n 1 (clojure.string/split seq #"\W+"))
     (partition n 1 seq))))

(n-gram 2 "I am an NLPer")
(n-gram 2 "I am an NLPer" :word)

;; 06
(def set-x (set (n-gram 2 "paraparaparadise")))
(def set-y (set (n-gram 2 "paragraph")))

(clojure.set/union set-x set-y) ;; 和
(clojure.set/intersection set-x set-y) ;; 積
(clojure.set/difference set-x set-y);; 差

(contains? set-x "se") ;; true
(contains? set-y "se") ;; false

;; 07
(defn template [x y z]
  (str x "時の" y "は" z))

(template 12 "気温"　22.4)

;; 08
(defn cipher [text]
  (apply str
         (map
          #(if (Character/isLowerCase %) (char (- 219 (int %))) %)
          text)))

(def text "I couldn't believe that I could actually understand what I was reading : the phenomenal power of the human mind .")
(def encoded (cipher text))
(def decoded (cipher encoded))
(= text decoded)
