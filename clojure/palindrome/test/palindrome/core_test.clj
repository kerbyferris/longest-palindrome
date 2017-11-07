(ns palindrome.core-test
  (:require [clojure.test :refer :all]
            [palindrome.core :refer :all]))

(def palindromes ["" "a" "aba" "abba"])
(def not-palindromes ["ab" "abax" "xabba"])

(deftest test-palindrome?
  (testing "Identifies palindrome"
    (assert (every? palindrome? palindromes)))
  (testing "Identifies non palindrome"
    (assert (not-any? palindrome? not-palindromes))))

(deftest test-get-pivot
  (testing "Gets pivot"
    (is "aa" (get-pivot "a", "a"))
    (is "aa" (get-pivot "aa", "b"))
    (is "a" (get-pivot "a" "b"))
    (is "abba" (get-pivot "abba" nil))
    (is "" (get-pivot "" "a"))))

(deftest test-update-longest
  (testing "Updates longest palindrome result set"
    (is ["x"] (update-longest "x" []))
    (is ["xy"] (update-longest "x" ["xy"]))
    (is ["x" "y"] (update-longest "x" ["y"]))))
