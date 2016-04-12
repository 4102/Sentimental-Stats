package term.project.SentimentalStats

/**
  * Singleton object for crawling the web or otherwise obtaining data.
  *
  * Takes a list of team objects containing their name and
  */
object WebCrawler {

  def search(subjects: List[String], options: Map[Symbol, Any]): List[Comment] = {

    val results = TwitterQuery.searchFor(subjects): List[Comment]

    println("Results:\n")
    //println(results.toString())
    println(results.mkString)

    DataFile.sample("Twitter Client Results:", results, 6)

    return results
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
case class Comment(time: String, text: String)
