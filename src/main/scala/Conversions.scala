package term.project.SentimentalStats

import java.time._
import java.util.Date
/**
  * Container for implicit conversions.
  */
object Conversions {

  /**
    * Converts java.util.Date to the superior java.time.LocalDate
    * Method body taken from http://stackoverflow.com/questions/21242110/
    */
  implicit def toLocalDate(date: Date): LocalDate = {
    date.toInstant.atZone(ZoneId.systemDefault()).toLocalDate
  }
}