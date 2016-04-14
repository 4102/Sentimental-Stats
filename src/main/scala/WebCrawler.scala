package term.project.SentimentalStats

import java.time._

case class Comment(time: LocalDate, text: String)

/**
  * Representations of teams from different sports/leagues for pattern-matching, etc.
  */
case class Team(name: String, home: String)
//case class RichTeam(sport: Symbol, league: Symbol, team: Team)

/**
  *
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

}