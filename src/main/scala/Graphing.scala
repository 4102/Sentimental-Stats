package term.project.SentimentalStats

import java.time.ZoneId._
import java.time.{ZoneId, LocalDate}
import java.util.Date
import java.util.Date.from
import java.time.Instant
import scalax.chart.api
import scalax.chart.api._

import term.project.SentimentalStats.Sentiment.Sentiment


/**
  * Creates graphs from structured data.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
trait Graphing extends Time {

  val timePrefix = '@'

  //implicit private val theme = org.jfree.chart.StandardChartTheme.createDarknessTheme

  /**
    * Constructs a file name for a graph.
    */
  private[this] def fileName(team: Team) = {
    team.name + team.seasonYear + timePrefix + currentDateTime
  }

  /**.
    * Constructs a series from a particular metric 0for plotting on a graph.
    */
  private def getMetricSeries(team: Team, metricName: String): api.TimeSeries with Object = {

    val array = for {
      date <- team.record.getDates.flatten
      stat <- team.record.getByName(metricName).flatten
      localDate = localDateFromString(integerToDateString(date.toInt))
      utilDate = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
    } yield (utilDate, stat)

    new TimeSeries(array.toSeq)
  }

  /**.
    * Constructs a series from sentiments for plotting on a graph.
    */
  private def getSentimentSeries(team: Team): Seq[(LocalDate, Sentiment)] = {

    team.sentimentRecord.toSeq
    /*
    for {
      s <- team.sentimentRecord
      date = LocalDatetoInteger(s._1)
      sentiment = s._2
    } yield (date, sentiment)
    */
  }

  /**
    * Creates a graph.
    */
  def graph(team: Team, options: Map[String, String]): Unit = {

    val title = team.name + "," + team.record.seasonInterval

    val data = getMetricSeries(team, "points")

    val chart = XYLineChart(data, title)

    val path = FilePaths.graphsDir + fileName(team) + ".jpg"
    chart.saveAsPNG(path)
  }

  /**
    * Creates a graph for each team in a list.
    */
  def graphAll(list: List[Team], options: Map[String, String]) = {
    list.foreach(graph(_, options))
  }
}