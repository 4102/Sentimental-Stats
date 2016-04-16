package term.project.SentimentalStats

/**
  * Retrieves content from web resources
  */
object WebCrawler {

  def search(teams: List[Team], options: Map[String, String]): List[Comment] = {

    val stats = SportsDatabase.getStatsFor(teams): List[String]
    SampleFile.sample("Stats", stats, 10)

    findComments(teams): List[Comment]
  }

  def findComments(teams: List[Team]): List[Comment] = {
    Twitter.search(teams.map(_.name))
    // query reddit, fb, etc.
  }
}