package term.project.SentimentalStats

/**
  * Valid sports, and the paths to files containing a list of relevant metrics.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
object Sports {

  val soccerMetrics = MetricsFile("soccerMetrics.txt").readLines()
  val basketballMetrics = MetricsFile("basketballMetrics.txt").readLines()
  val footballMetrics = MetricsFile("footballMetrics.txt").readLines()
  val baseballMetrics = MetricsFile("baseballMetrics.txt").readLines()
  val hockeyMetrics = MetricsFile("hockeyMetrics.txt").readLines()

  sealed abstract class Sport(val name: String, val metrics: List[String])

  case object Soccer extends Sport("Soccer", soccerMetrics)
  case object Basketball extends Sport("Basketball", basketballMetrics)
  case object Football extends Sport("Football", footballMetrics)
  case object Baseball extends Sport("Baseball", baseballMetrics)
  case object Hockey extends Sport("Hockey", hockeyMetrics)
}