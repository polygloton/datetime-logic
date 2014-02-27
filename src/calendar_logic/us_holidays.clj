(ns calendar-logic.us-holidays
  (:refer-clojure :exclude [==])
  (:use clojure.core.logic)
  (:require [calendar-logic.gregorian :refer :all :as greg]
      [clojure.core.logic.fd :as fd]))

; http://www.usa.gov/citizens/holidays.shtml

(defn- first-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 1 7))
    (== day day_)))

(defn- second-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 8 14))
    (== day day_)))

(defn- third-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 15 21))
    (== day day_)))

(defn- fourth-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 22 28))
    (== day day_)))

(defn- fifth-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 29 31))
    (== day day_)))

(defn- last-week-31 [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 25 31))
    (== day day_)))

(defn- last-week-30 [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 24 30))
    (== day day_)))

(defn new-years-day [year month-num day]
  (fresh [year_]
         (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
         (== year year_)
         (== month-num 1)
         (== day 1)))

(defn not-new-years-day [year month-num day]
  (fresh [year_]
         (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
         (== year year_)
         (== month-num 1)
         (!= day 1)
         (greg/day-in-month year month-num day)))

(defn mlk-bday [year month-num day]
  (fresh [year' month-num' day']
         (fd/in year' (fd/interval 1 Integer/MAX_VALUE))
         (== year year')
         (third-week day')
         (== day day')
         (== month-num' 1)
         (== month-num month-num')
         (greg/day-of-the-week year month-num day :monday)))

(defn not-mlk-bday [year month-num day]
  (fresh [year' month-num']
         (fd/in year' (fd/interval 1 Integer/MAX_VALUE))
         (== year year')
         (greg/month month-num')
         (== month-num month-num')
         (fresh [dow]
                (greg/day-of-the-week year month-num day dow)
                (conde
                 [(first-week day)]
                 [(second-week day)]
                 [(third-week day) (!= [month-num dow] [1 :monday])]
                 [(fourth-week day)]
                 [(fifth-week day)]))))

(defn groundhog-day [year month-num day]
  (fresh [year']
         (fd/in year' (fd/interval 1 Integer/MAX_VALUE))
         (== year year')
         (== month-num 2)
         (== day 2)))

(defn not-groundhog-day [year month-num day]
  (fresh [year']
         (fd/in year' (fd/interval 1 Integer/MAX_VALUE))
         (== year year')
         (!= [month-num day] [2 2])
         (greg/day-in-month year month-num day)))

(defn valentines-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 2)
    (== day 14)))

(defn washington-bday [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (third-week day_)
    (== day day_)
    (== month_ 2)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :monday)))

(defn earth-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 4)
    (== day 22)))

(defn arbor-day [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (last-week-30 day_)
    (== day day_)
    (== month_ 4)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :friday)))

(defn mothers-day [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (second-week day_)
    (== day day_)
    (== month_ 5)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :sunday)))

(defn memorial-day [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (last-week-31 day_)
    (== day day_)
    (== month_ 5)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :monday)))

(defn flag-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 6)
    (== day 14)))

(defn fathers-day [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (third-week day_)
    (== day day_)
    (== month_ 6)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :sunday)))

(defn independence-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 7)
    (== day 4)))

(defn labor-day [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (first-week day_)
    (== day day_)
    (== month_ 9)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :monday)))

(defn patriot-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 9)
    (== day 11)))

(defn columbus-day [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (second-week day_)
    (== day day_)
    (== month_ 10)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :monday)))

(defn halloween [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 10)
    (== day 31)))

(defn veterans-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 11)
    (== day 11)))

(defn thanksgiving-day [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (fourth-week day_)
    (== day day_)
    (== month_ 11)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :thursday)))

(defn pearl-harbor-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 12)
    (== day 7)))

(defn christmas-eve [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 12)
    (== day 24)))

(defn christmas-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 12)
    (== day 25)))

(defn new-years-eve [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 12)
    (== day 31)))

(defn federal-holiday [year month-num day holiday]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year_ year)
    (fd/in month_ (fd/interval 1 12))
    (== month_ month-num)
    (fd/in day_ (fd/interval 1 31))
    (== day_ day)
    (conde
      [(new-years-day year_ month_ day_) (== holiday :new-years-day)]
      [(mlk-bday year_ month_ day_) (== holiday :mlk-bday)]
      [(washington-bday year_ month_ day_) (== holiday :washington-bday)]
      [(memorial-day year_ month_ day_) (== holiday :memorial-day)]
      [(independence-day year_ month_ day_) (== holiday :independence-day)]
      [(labor-day year_ month_ day_) (== holiday :labor-day)]
      [(columbus-day year_ month_ day_) (== holiday :columbus-day)]
      [(veterans-day year_ month_ day_) (== holiday :veterans-day)]
      [(thanksgiving-day year_ month_ day_) (== holiday :thanksgiving-day)]
      [(christmas-day year_ month_ day_) (== holiday :christmas-day)])))
