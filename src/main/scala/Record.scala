package term.project.SentimentalStats

import java.time.LocalDate
import term.project.SentimentalStats.Conversions._

class Record(val statNames: List[String],
             val statValues: Array[Array[Option[Double]]]) {

  def getSeasonInterval: Interval = {

    val localDates = for {
      dateOption <- getByName("date")
      date <- dateOption
      formattedDate = integerToLocalDate(date.toInt)
    } yield LocalDate.parse(formattedDate)

    new Interval(localDates.head, localDates.last)
  }

  /**
    * The names of stats collected are in stats.txt if you need to find them.
    */
  def getByName(name: String): Array[Option[Double]] = {
    val index = statNames.indexOf(name)
    statValues(index)
  }
}
