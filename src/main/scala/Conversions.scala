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

  case class Year(year: Int) {
    require( year<10000 && year>999 ) // year should have 4 digits
  }
}