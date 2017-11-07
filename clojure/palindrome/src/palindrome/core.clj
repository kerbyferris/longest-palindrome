(ns palindrome.core)

(defn palindrome?[str]
  (let [half (quot (count str) 2)]
    (= (take half str)
       (take half (reverse str)))))

(defn get-pivot[current next]
  (cond
    (> (count current) 1) current
    (= current next) (str current next)
    :else (str current)))

(defn update-longest[candidate-palindrome longest-palindromes]
  (let [longest (count (first longest-palindromes))
        candidate-length (count candidate-palindrome)]
    (cond
      (< candidate-length longest) longest-palindromes
      (> candidate-length longest) [candidate-palindrome]
      :else (set (cons candidate-palindrome longest-palindromes)))))

(defn get-next-candidate[pivot processed unprocessed]
  (let [reach-left (dec (quot (count pivot) 2))
        reach-right (if (even? (count pivot))
                      (inc reach-left)
                      reach-left)
        maybe-start (first (drop reach-left (reverse processed)))
        maybe-end (first (drop reach-right unprocessed))]
    (if (and maybe-start maybe-end)
      (str maybe-start pivot maybe-end))))

(defn get-palindrome[string]
  (loop [processed ""
         unprocessed string
         current (str (first unprocessed))
         longest []]
    (if (= "" unprocessed)
      longest
      (let [pivot (get-pivot current (str (first (rest unprocessed))))
            maybe-palindrome (get-next-candidate pivot processed unprocessed)
            next-processed (str processed (first unprocessed))
            next-unprocessed (apply str (rest unprocessed))]
        (do
          (println ;"reach" reach "\t"
                   "current: " current"\t"
                   "proc: " processed "\t"
                   "unproc: " unprocessed "\t"
                   "pivot " pivot "\t"
                   "maybe p: " maybe-palindrome)
          (cond
            (nil? maybe-palindrome) (recur next-processed
                                           next-unprocessed
                                           (str (first next-unprocessed))
                                           longest)
            (palindrome? maybe-palindrome) (recur processed
                                                  unprocessed
                                                  maybe-palindrome
                                                  (update-longest maybe-palindrome longest))
            :else (recur next-processed
                         next-unprocessed
                         (str (first next-unprocessed))
                         (update-longest pivot longest))))))))

(defn -main[]
  ; (println (get-palindrome "xabbablahblahracecar1234321"))
  (println (get-palindrome "abba"))
  ; (println (get-palindrome "aba"))
  ; (println (get-palindrome "abaaaabaaa"))
  )
