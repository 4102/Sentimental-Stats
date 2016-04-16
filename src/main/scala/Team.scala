package term.project.SentimentalStats

import term.project.SentimentalStats.Sport._
/**
  * Representations of teams from different sports/leagues for pattern-matching, etc.
  */
class TeamID(val name: String,
             val home: String,
             val league: String,
             val sport: Sport)

object TeamID {

  def apply(name: String,
            home: String,
            league: String,
            sport: Sport
           ): TeamID = {
    new TeamID(name, home, league.toUpperCase, sport)
  }
}

/**
  *
  */
trait Roster

/**
  *
  */
trait Coaches {
  val HeadCoach = ""// head coach at beginning of the season.
  val SecondaryCoaches = ""// acting head coach at any point during the season.
}

//class RichTeam(sport: Symbol, league: Symbol, team: Team)
// extend Team
// with Roster
// with Coaches
//
