package term.project.SentimentalStats

/**
  * Singleton object for crawling the web or otherwise obtaining data.
  */
object WebCrawler {

  def search(teamName : String, teamHome : String): List[Comment] = {

    val log = new File("log.txt", false)

    val results: List[Comment] = TwitterQuery.searchFor(teamName)

    log.sample("Twitter Client Results:", results, 6)

    //results :+ reddit.query(teamName)
    //etc

    return results
  }

  // To-do: disambiguate queries
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
case class Comment(author : String, time: String, text: String)
