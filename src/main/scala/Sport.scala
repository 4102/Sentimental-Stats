package term.project.SentimentalStats

/**
  * Sports
  */
object Sport {

  val soccerStats = List("")
  val basketballStats = StatsFile("basketballStats.txt").readLines()
  val footballStats = List("")
  val baseballStats = List("")
  val hockeyStats = List("")

  sealed abstract class Sport(val name: String, val stats: List[String])

  case object Soccer extends Sport("Soccer", soccerStats)
  case object Basketball extends Sport("Basketball", basketballStats)
  case object Football extends Sport("Football", footballStats)
  case object Baseball extends Sport("Baseball", baseballStats)
  case object Hockey extends Sport("Hockey", hockeyStats)
}