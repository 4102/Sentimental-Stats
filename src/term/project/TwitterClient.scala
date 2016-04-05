package term.project

// Twitter4J is a Java library, which Scala can call with little extra effort.
import twitter4j._
import twitter4j.conf.ConfigurationBuilder
import collection.JavaConversions._
import java.util

/**
  * Singleton object that handles queries to Twitter's REST API.
  * Based on Twitter4J's java tutorial: http://twitter4j.org/en/configuration.html
  */
object TwitterQuery extends TwitterClient {

  def searchFor(searchTerm: String): Array[String] = {

    // Scala implicitly converts twitter4j's Java list to a Scala (linked) List.
    val tweets: util.List[Status] = twitter.search(new Query(searchTerm)).getTweets

    tweets.foreach( tweets => println(tweets.getCreatedAt + tweets.getText))

    //val result : Array[String] =

    return result
  }
}

/**
  * Configures and instantiates a twitter4j twitter client
  */
trait TwitterClient {

  val cb = new ConfigurationBuilder()
  cb.setDebugEnabled(true)
    .setOAuthConsumerKey("2uME8OtjVYNpTyRZaU1Gtelsd")
    .setOAuthConsumerSecret("IBwIjuNEmtW3uWCKSn4IGw9ScQa8BG14n7fa7TQD9n0YQ3lyXr")
    .setOAuthAccessToken("717095311433469952-CDHgevAGNEM10B7oHZ2rF0NkK8JVnW8")
    .setOAuthAccessTokenSecret("ch2m305L2uug3bQPaNOFgQ6CJkdiWBnzsPU4aWWCe0Vxg")

  val twitter = new TwitterFactory(cb.build()).getInstance()
}

  /**
    *  To-do: read keys/secrets/tokens from a file instead of hardcoded,
    *       : improve use of futures: http://doc.akka.io/docs/akka/snapshot/scala/futures.html
    */