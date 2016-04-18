package term.project.SentimentalStats

import scalax.chart.api._

object Grapher extends Time {

  private val dir = "charts/"

  object Format {
    sealed abstract class Format(val suffix: String)
    case object Png extends Format(".png")
    case object Jpeg extends Format(".jpg")
    case object Pdf extends Format(".pdf")
    case object Svg extends Format(".svg")
  }

  def fileName(team: Team) = {
    team.name + team.seasonYear + "@" + currentDateTime
  }

  def graph(team: Team, options: Map[String, String]): Unit = {

    val title = team.name + "," + team.seasonInterval

    val data = for (i <- 1 to 5) yield (i,i)

    val path = dir + fileName(team) + Format.Jpeg.suffix
    val chart = XYLineChart(data, title)
    chart.saveAsPNG(path)
    //chart.show()
  }

  def graphAll(list: List[Team], options: Map[String, String]) = {
    list.foreach(graph(_, options))
  }
}