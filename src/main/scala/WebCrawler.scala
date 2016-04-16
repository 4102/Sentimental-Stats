package term.project.SentimentalStats

/**
  * Retrieves content from web resources
  */
object WebCrawler {

  def search(teams: List[Team], options: Map[String, String]): List[Comment] = {

    SportsDatabase.getStatsFor(teams): List[String]
    findComments(teams): List[Comment]
  }

  def findComments(teams: List[Team]): List[Comment] = {
    TwitterQuery.search(teams.map(_.name))
    // query reddit, fb, etc.
  }
}