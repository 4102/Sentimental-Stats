package term.project.SentimentalStats

// Twitter4J is a Java library, which Scala can call with little extra effort.
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

import java.time._
import java.util // IntelliJ warns this is unused, but getTweets() needs it.
import collection.JavaConversions._


/**
  * Configure a Twitter4J twitter client with OAuth credentials from a file.
  * Based on a tutorial: http://twitter4j.org/en/configuration.html
  */
abstract class AuthenticatedTwitterClient {

  // about as functional as Java setters can be made.
  private val cb = {
    val keys = ConfigFile.extractValues()
    new ConfigurationBuilder()
      .setDebugEnabled(keys.head.toBoolean)
      .setOAuthConsumerKey(keys(1))
      .setOAuthConsumerSecret(keys(2))
      .setOAuthAccessToken(keys(3))
      .setOAuthAccessTokenSecret(keys(4))
  }

  val twitter = new TwitterFactory(cb.build()).getInstance()
}

/**
  * Search Twitter for tweets containing a term.
  *
  * !!Twitter rate-limits searches to 450 per 15 minutes!!
  */
object TwitterQuery extends AuthenticatedTwitterClient {

  val time = LocalDateTime.now()

  def searchFor(searchTerms: List[String]): List[Comment] = {

    def singleQuery(term: String): List[Comment] = {
      val query = new Query(term)
      query.setCount(100) // max allowed
      query.setResultType(Query.ResultType.popular) // as opposed to recent

      twitter.search(query)
        .getTweets.toList
        .map(tweet => Comment(Utils.toLocalDate(tweet.getCreatedAt), tweet.getText))
    }

    searchTerms.flatMap(singleQuery(_))
  }
}