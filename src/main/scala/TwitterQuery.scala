package term.project.SentimentalStats

import twitter4j._
import twitter4j.conf.ConfigurationBuilder
import twitter4j.Query.ResultType._

import collection.JavaConversions._
import term.project.SentimentalStats.Conversions._


/**
  * Configure a Twitter4J twitter client with OAuth credentials from a file.
  * Based on a tutorial: http://twitter4j.org/en/configuration.html
  */
trait AuthenticatedTwitterClient {

  // about as functional as Java setters can be made.
  private val cb = {
    val config = ConfigFile.data.toMap: Map[String, String]

    new ConfigurationBuilder()
      .setDebugEnabled(
        config.getOrElse("Debug Enabled", "true").toBoolean
      )
      .setOAuthConsumerKey(
        config.getOrElse("Oauth Consumer Key", "NoKey")
      )
      .setOAuthConsumerSecret(
        config.getOrElse("Oauth Consumer Secret", "NoSecret")
      )
      .setOAuthAccessToken(
        config.getOrElse("Oauth Access Token", "NoToken")
      )
      .setOAuthAccessTokenSecret(
        config.getOrElse("Oauth Access Secret", "NoToken")
      )
  }

  val twitter = new TwitterFactory(cb.build()).getInstance()
}

/**
  * Query Twitter for tweets containing a term.
  */
object TwitterQuery extends AuthenticatedTwitterClient {

  def searchFor(searchTerms: List[String]): List[Comment] = {

    def singleQuery(term: String): List[Comment] = {
      val query = setupQuery(term)

      twitter.search(query)
        .getTweets.toList
        .map(tweet => Comment(tweet.getCreatedAt, tweet.getText))
    }

    searchTerms.flatMap(singleQuery(_))
  }

  private def setupQuery(term: String): Query = {
    val query = new Query(term)
    query.setCount(100) // max
    query.setResultType(popular)
    query
  }

}