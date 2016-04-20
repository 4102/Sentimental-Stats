package term.project.SentimentalStats

import twitter4j._
import twitter4j.Query.ResultType._
import collection.JavaConversions._

/**
  * Queries Twitter, using the Twitter4J library.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
object Twitter extends TwitterAuthentication with Time{

  /**
    * Searches for references to the search-term during an interval.
    */
  def searchInterval(searchTerm: String, interval: Interval): List[Comment] = {

      val query = setupQuery(searchTerm, interval)

      twitter.search(query)
        .getTweets.toList
        .map(tweet => Comment(tweet.getCreatedAt, tweet.getText))
  }

  /**
    * Sets up a Twitter4J Query.
    */
  private def setupQuery(term: String, interval: Interval): Query = {

    val query = new Query(term)
      query.setCount(100) // max
      query.setResultType(popular)
      query.setSince(interval.begin.toString)
      query.setUntil(interval.end.toString)
    return query
  }
}