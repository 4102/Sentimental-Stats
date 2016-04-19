package term.project.SentimentalStats

import scalax.chart.api._

/**
  * Creates graphs from structured data.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
trait Graphing extends Time {

  val dir = FilePaths.graphsDir

  val timePrefix = '@'

  //implicit private val theme = org.jfree.chart.StandardChartTheme.createDarknessTheme

  /**
    * Constructs a file name for a graph.
    */
  private[this] def fileName(team: Team) = {
    team.name + team.seasonYear + timePrefix + currentDateTime
  }

  /**
    * Creates a graph for the
    */
  def graph(team: Team, options: Map[String, String]): Unit = {

    val title = team.name + "," + team.seasonInterval
    val data = for (i <- 1 to 5) yield (i,i) // testtestest don't forget to make this actually do something
    val path = dir + fileName(team) + ".jpg"

    val chart = XYLineChart(data, title)

    chart.saveAsPNG(path)
    //chart.show()
  }

  def graphAll(list: List[Team], options: Map[String, String]) = {
    list.foreach(graph(_, options))
  }
}