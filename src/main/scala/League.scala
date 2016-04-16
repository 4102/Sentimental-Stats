package term.project.SentimentalStats

import term.project.SentimentalStats.Sport._
import scala.sys.error

/**
  * Valid leagues
  */
object League {
  sealed abstract class League(val name: String, val sport: Sport)

  case object NCAABB extends League("ncaabb", Basketball)
  case object NBA extends League("nba", Basketball)
  case object NCAAF extends League("ncaafb", Football)
  case object NFL extends League("nfl", Football)
  case object MLB extends League("mlb", Baseball)
  case object NHL extends League("nhl", Hockey)

  def stringToLeague(leagueName: String): League = {

    val leagues = Array(NCAABB, NBA, NCAAF, NFL, MLB, NHL)

    leagues.find(leagueName.toUpperCase == _.toString) match {
      case Some(league) => league
      case None => error("Could not identify league. Please re-enter.")
    }
  }
}