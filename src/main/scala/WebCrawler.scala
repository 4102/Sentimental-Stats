package term.project.SentimentalStats

/**
  * Retrieves content from web resources
  */
object WebCrawler {

  def search(teams: List[Team], options: Map[String, String]): List[Comment] = {

    TwitterQuery.searchFor(teams.map(_.name)) // search for teams by their name

    SportsDBQuery.searchFor(teams)

    //results :+ reddit.query(teamName)
    //etc
  }

  // To-do: disambiguate queries
  // implement asynchronous concurrent processes with Futures
  // GenerateQueries()
  // QueryOfficial()
  // QuerySportsReference()

}