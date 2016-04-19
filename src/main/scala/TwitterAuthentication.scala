package term.project.SentimentalStats

import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

/**
  * Configure a Twitter4J twitter client with OAuth credentials from a file.
  * Based on a tutorial: http://twitter4j.org/en/configuration.html
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
trait TwitterAuthentication {

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
