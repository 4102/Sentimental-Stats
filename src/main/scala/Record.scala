package term.project.SentimentalStats

import java.time.LocalDate

/**
  * A record of the metrics and dates characterizing a team's performance over a season.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
class Record(val statNames: List[String],
             val statValues: Array[Array[Option[Double]]]) extends Time {

  /**
    * Gets the interval over which a season occurred.
    */
  lazy val seasonInterval: Interval = {

    val localDates = for {
      dateOption <- getByName("date")
      date <- dateOption
      formattedDate = integerToDateString(date.toInt)
    } yield LocalDate.parse(formattedDate)

    new Interval(localDates.head, localDates.last)
  }

  /**
    * Gets the array of stats by the name of the metric.
    */
  def getByName(name: String): Array[Option[Double]] = {
    val index = statNames.indexOf(name)
    statValues(index)
  }

  /**
    * Gets the dates in particular.
    */
  def getDates: Array[Option[Double]] = {
    val index = statNames.indexOf("date")
    statValues(index)
  }
}
