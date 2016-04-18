package term.project.SentimentalStats

import java.time.{ZoneId, LocalDate, LocalDateTime}
import java.time.temporal.ChronoUnit._
import java.util.Date

/**
  * Handles the parsing and creation of units of time.
  */
trait Time {

  def currentDateTime = LocalDateTime.now.truncatedTo(MINUTES).toString

  /**
    * Converts and integer in yyymmdd format to a LocalDate
    */
  def integerToLocalDate(i: Int) = {
    val (y,md) = i.toString.splitAt(4)
    val  (m,d) = md.splitAt(2)
    y + "-" + m + "-" + d
  }

  /**
    * Converts java.util.Date to the superior java.time.LocalDate
    * Method body taken from http://stackoverflow.com/questions/21242110/
    */
  implicit def toLocalDate(date: Date): LocalDate = {
    date.toInstant.atZone(ZoneId.systemDefault()).toLocalDate
  }
}
