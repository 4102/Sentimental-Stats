package term.project.SentimentalStats

import java.time.LocalDate

/**
  * An interval of two dates, the earlier first.
  */
case class Interval(begin: LocalDate, end: LocalDate)