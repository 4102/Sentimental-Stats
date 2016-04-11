package term.project.SentimentalStats

// Twitter4J is a Java library, which Scala can call with little extra effort.
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

import java.util // IntelliJ warns this is unused, but getTweets() needs it.
import collection.JavaConversions._ // Implicit conversions for Scala methods called on java collections.


/**
  * Configure a Twitter4J twitter client with OAuth credentials from a file.
  * Based on a tutorial: http://twitter4j.org/en/configuration.html
  */
abstract class AuthenticatedTwitterClient {

  private val cb = {
    val keys = KeyFile.extractKeys()
    new ConfigurationBuilder()
      .setDebugEnabled(true)
      .setOAuthConsumerKey(keys.head)
      .setOAuthConsumerSecret(keys(1))
      .setOAuthAccessToken(keys(2))
      .setOAuthAccessTokenSecret(keys(3))
  }

  val twitter = new TwitterFactory(cb.build()).getInstance()
}

/**
  * Search Twitter for tweets containing a term.
  */
object TwitterQuery extends AuthenticatedTwitterClient {

  def searchFor(searchTerm: String): List[Comment] = {

    var query = new Query(searchTerm)
        query.setCount(100) // max allowed

    val results = twitter.search(query)
                    .getTweets.toList
                    .map( tweet => Comment(tweet.getCreatedAt.toString, tweet.getText) )
                    :List[Comment]

    /* Same function composition as a for-comprehension.
    val results = (
        for {tweet <- twitter.search(query).getTweets}
         yield Comment(tweet.getCreatedAt.toString, tweet.getText)
      ).toList: List[Comment]
      */

    return results
  }
}