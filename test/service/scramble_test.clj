(ns service.scramble-test
  (:require [clojure.test :refer :all]
            [service.scramble :refer [scramble?]]))

(deftest scramble-test
  (testing "(scramble? “rekqodlw” ”world”) ==> true"
    (is (= (scramble? "rekqodlw" "world") true)))

  (testing "(scramble? “cedewaraaossoqqyt” ”codewars”) ==> true"
    (is (= (scramble? "cedewaraaossoqqyt" "codewars") true)))

  (testing "(scramble? “katas”  “steak”) ==> false"
    (is (= (scramble? "katas" "steak") false))))
