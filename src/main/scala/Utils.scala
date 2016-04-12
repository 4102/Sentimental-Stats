package term.project.SentimentalStats

import java.time._
import java.util.Date
/**
  * Miscellaneous functions and such.
  */
object Utils {

  /**
    * Converts java.util.Date to the superior java.time.LocalDate
    *
    * Taken from https://stackoverflow.com/questions/21242110/
    */
  def toLocalDate(date: Date): LocalDate = {
    date.toInstant.atZone(ZoneId.systemDefault()).toLocalDate
  }
}