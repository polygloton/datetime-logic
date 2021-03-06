(ns calendar-logic.test.gregorian.month-days-in-year-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.gregorian :only [month-days-in-year]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest month-days-in-year-test

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (month-days-in-year 2013 1 q))
    => [31])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (month-days-in-year 2013 12 q))
    => [365])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (month-days-in-year 2013 10 q))
    => [304])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (month-days-in-year 2013 q 304))
    => [10])
  )
