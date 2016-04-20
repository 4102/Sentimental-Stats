package term.project.SentimentalStats

import java.time.LocalDate

import scalax.chart.api._
import scalax.chart.api

import term.project.SentimentalStats.Sentiment.Sentiment


/**
  * Creates graphs from structured data.
  * This class is very unfinished.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
trait Graphing extends Time {

  val timePrefix = '@'

  /**
    * Constructs a file name for a graph.
    */
  private[this] def fileName(team: Team) = {
    team.name + team.seasonYear + timePrefix + currentDateTime
  }

  /**.
    * Constructs a series from a particular metric for plotting on a graph.
    */
  private def getMetricSeries(team: Team, metricName: String) = {

    val array = for {
      date <- team.record.getDates.flatten
      stat <- team.record.getByName(metricName).flatten
    } yield (date, stat)

    array.toSeq
  }

  /**.
    * Constructs a series from sentiments for plotting on a graph.
    */
  private def getSentimentSeries(team: Team): Seq[(LocalDate, Sentiment)] = {

    team.sentimentRecord.toSeq
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

    chart.show()
  }

  /**
    * Creates a graph for each team in a list.
    */
  def graphAll(list: List[Team], options: Map[String, String]) = {
    list.foreach(graph(_, options))
  }
}