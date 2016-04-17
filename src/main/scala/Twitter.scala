package term.project.SentimentalStats

import twitter4j._
import twitter4j.Query.ResultType._
import collection.JavaConversions._
import term.project.SentimentalStats.Conversions._

/**
  * Query Twitter for tweets containing a term.
  */
object Twitter extends TwitterAuthentication {

  /**
    * Searches for references to the search-term during an interval.
    */
  def searchOverPeriod(searchTerm: String, interval: String): List[Comment] = {

      val query = setupQuery(searchTerm, interval)

      twitter.search(query)
        .getTweets.toList
        .map(tweet => Comment(tweet.getCreatedAt, tweet.getText))
  }

  /**
    * Sets up a Twitter4J Query.
    */
  private def setupQuery(term: String, interval: String): Query = {
    val dates = interval.split(",")
    val query = new Query(term)
      query.setCount(100) // max
      query.setResultType(popular)
      query.setSince(dates.head)
      query.setUntil(dates.last)
    return query
  }

}