package term.project.SentimentalStats

// Twitter4J is a Java library, which Scala can call with little extra effort.
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

import java.util // IDE may think this is unused, but getTweets() returns a util.List defined here.
import collection.JavaConversions._ // Implicit conversions for Scala methods called on java collections.


/**
  * Configure a Twitter4J twitter client with OAuth credentials from a file.
  *
  * Based on a Twitter4J tutorial: http://twitter4j.org/en/configuration.html
  * Traits are basically classes that
  */
trait TwitterClient {

  private val keys = new File("twitterConfig.txt", true).extractKeys()

  val cb = {
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
object TwitterQuery extends TwitterClient {

  def searchFor(searchTerm: String): List[Comment] = {

    val query = new Query(searchTerm)
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