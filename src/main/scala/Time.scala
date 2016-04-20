package term.project.SentimentalStats

import java.time.{ZoneId, LocalDate, LocalDateTime}
import java.time.temporal.ChronoUnit._
import java.util.Date


/**
  * Handles the parsing and creation of units of time.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
trait Time {

  def currentDateTime = LocalDateTime.now.truncatedTo(MINUTES).toString

  /**
    * Converts an integer in yyymmdd format to a LocalDate.
    */
  def integerToDateString(i: Int) = {
    val (y,md) = i.toString.splitAt(4)
    val  (m,d) = md.splitAt(2)
    y + "-" + m + "-" + d
  }

  /**
    * LocalDate from Integer
    */
  def localDateFromInt(dateInt: Int): LocalDate = {
    localDateFromString(integerToDateString(dateInt))
  }

  /**
    * LocalDate from String
    */
  def localDateFromString(dateString: String): LocalDate = {
    LocalDate.parse(integerToDateString(dateString.toInt))
  }

  /**
    * Converts a LocalDate to an integer.
    */
  def LocalDatetoInteger(date: LocalDate) = {
    date.toString.replace("-","").toInt
  }

  def toOldJavaDate(localDate: LocalDate) = {
    Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
  }

  /**
    * Converts java.util.Date to the superior java.time.LocalDate
    * Method body taken from http://stackoverflow.com/questions/21242110/
    */
  implicit def toLocalDate(date: Date): LocalDate = {
    date.toInstant.atZone(ZoneId.systemDefault()).toLocalDate
  }
}
