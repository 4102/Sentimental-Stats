package term.project.SentimentalStats

/**
  * Sports
  */
object Sports {

  val soccerStats = StatsFile("soccerStats.txt").readLines()
  val basketballStats = StatsFile("basketballStats.txt").readLines()
  val footballStats = StatsFile("footballStats.txt").readLines()
  val baseballStats = StatsFile("baseballStats.txt").readLines()
  val hockeyStats = StatsFile("hockeyStats.txt").readLines()

  sealed abstract class Sport(val name: String, val stats: List[String])

  case object Soccer extends Sport("Soccer", soccerStats)
  case object Basketball extends Sport("Basketball", basketballStats)
  case object Football extends Sport("Football", footballStats)
  case object Baseball extends Sport("Baseball", baseballStats)
  case object Hockey extends Sport("Hockey", hockeyStats)
}