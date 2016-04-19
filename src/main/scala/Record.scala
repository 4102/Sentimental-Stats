package term.project.SentimentalStats

import java.time.LocalDate

/**
  * A record of the stats and dates characterizing a team's performance over a season.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
class Record(val statNames: List[String],
             val statValues: Array[Array[Option[Double]]]) extends Time {

  /**
    * Gets the interval over which a season occurred.
    */
  def getSeasonInterval(): Interval = {

    val localDates = for {
      dateOption <- getByName("date")
      date <- dateOption
      formattedDate = integerToLocalDate(date.toInt)
    } yield LocalDate.parse(formattedDate)

    new Interval(localDates.head, localDates.last)
  }

  /**
    * The names of tracked stats are in settings/stats.txt if you need to find them.
    */
  def getByName(name: String): Array[Option[Double]] = {
    val index = statNames.indexOf(name)
    statValues(index)
  }
}
