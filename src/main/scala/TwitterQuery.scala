package term.project.SentimentalStats

// Twitter4J is a Java library, which Scala can call with little extra effort.
import twitter4j._
import twitter4j.conf.ConfigurationBuilder
import java.util
import collection.JavaConversions._

/**
  * Configures and instantiates a twitter4j twitter client
  * Based on a Twitter4J tutorial: http://twitter4j.org/en/configuration.html
  */
trait TwitterClient {

  val lines: List[String] = new File("twitterConfig.txt", true).extractKeys() // don't overwrite!

  val cb = new ConfigurationBuilder()
  cb.setDebugEnabled(true)
    .setOAuthConsumerKey("2uME8OtjVYNpTyRZaU1Gtelsd")
    .setOAuthConsumerSecret("IBwIjuNEmtW3uWCKSn4IGw9ScQa8BG14n7fa7TQD9n0YQ3lyXr")
    .setOAuthAccessToken("717095311433469952-CDHgevAGNEM10B7oHZ2rF0NkK8JVnW8")
    .setOAuthAccessTokenSecret("ch2m305L2uug3bQPaNOFgQ6CJkdiWBnzsPU4aWWCe0Vxg")

  val twitter = new TwitterFactory(cb.build()).getInstance()
}

/**
  * Singleton object that handles queries to Twitter's REST API.
  */
object TwitterQuery extends TwitterClient {

  def searchFor(searchTerm: String): List[Comment] = {

    // util.List is a Java List, not a Scala linked List
    val tweets: util.List[Status] = twitter.search(new Query(searchTerm)).getTweets

    // Java List is implicitly converted to Scala Buffer
    val results: List[Comment] = (
      for {
          tweet <- tweets
          comment = Comment(tweet.getUser.toString, tweet.getCreatedAt.toString, tweet.getText)
          } yield comment
      )(collection.breakOut) // then explicitly to the expected type

    return results
  }
}
