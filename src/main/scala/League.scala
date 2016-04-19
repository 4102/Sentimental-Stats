package term.project.SentimentalStats

import term.project.SentimentalStats.Sports._
import scala.sys.error

/**
  * League names that sportsdatabase.com will accept as valid.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
object League {

  sealed abstract class League(val name: String, val sport: Sport)

  case object NCAABB extends League("ncaabb", Basketball)
  case object NBA extends League("nba", Basketball)
  case object NCAAF extends League("ncaafb", Football)
  case object NFL extends League("nfl", Football)
  case object MLB extends League("mlb", Baseball)
  case object NHL extends League("nhl", Hockey)

  /**
    * Gets the league corresponding to a string, if possible,
    * and throws an exception if not.
    */
  def stringToLeague(leagueName: String): League = {

    val leagues = Array(NCAABB, NBA, NCAAF, NFL, MLB, NHL)

    leagues.find(leagueName.toUpperCase == _.toString) match {
      case Some(league) => league
      case None => error("Could not identify league.")
    }
  }
}