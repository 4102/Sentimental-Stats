package term.project.SentimentalStats

import java.time.LocalDate

/**
  * An interval of two dates, the earlier first.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
case class Interval(begin: LocalDate, end: LocalDate)