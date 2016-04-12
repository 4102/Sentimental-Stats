package term.project.SentimentalStats

import java.time._

/**
  * Singleton object for crawling the web or otherwise obtaining data.
  */
object WebCrawler {

  def search(teams: List[Team], options: Map[Symbol, Any]): List[Comment] = {

    TwitterQuery.searchFor(teams.map(_.name)) // search for teams by their name

    //results :+ reddit.query(teamName)
    //etc
  }

  // To-do: disambiguate queries
  // implement asynchronous concurrent processes with Futures
  // GenerateQueries()
  // QueryOfficial()
  // QuerySportsReference()

  //def collectData(sport: String, league: String, team: String): SanitaryData = {
}

/**
  * Container for comment data from any source.
  *
  * Case classes are plain data containers with extra functionality for pattern matching.
  */
case class Comment(time: LocalDate, text: String)

/**
  * Representations of teams from different sports/leagues for pattern-matching, etc.
  *
  * Extending a sealed abstract class allows for exhaustive pattern matching.
  * It might seems as though Team should be the base, but it is better as a case itself.
  */
sealed abstract class TeamBase
case class Team(name: String, home: String) extends TeamBase
//case class ExpTeam(team: Team, x: Int) extends Team(team.name, team.home) // dependency injection
/* gaaaaah
case class DisambiguatedTeam(sport: Symbol, league: Symbol) extends Team(name, home)
case class NCAAMTeam() extends DisambiguatedTeam('basketball, 'ncaam)
case class NCAAWTeam() extends DisambiguatedTeam('basketball, 'ncaaw)
case class NBATeam() extends DisambiguatedTeam('basketball, 'nba)
case class WNBATeam() extends DisambiguatedTeam('basketball, 'wnba)
*/
