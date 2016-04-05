package term.project

import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._

/**
  * Singleton object for crawling the web or otherwise obtaining data.
  */

object WebCrawler {

  def search(teamName : String, teamHome : String): Array[String] = {

    val results = Array[String]()

    val twitter = new TwitterClient
    //val reddit = new RedditClient
    //etc

    results :+ TwitterQuery.searchFor(teamName)
    //results :+ reddit.query(teamName)
    //etc

    return results
  }

  // To-do: disambiguate queries
  // GenerateQueries()

  // QueryOfficial()

  // QuerySportsReference()

  // collectData should domain/page discovery, data scraping, and data sanitizing.
  //def collectData(sport: String, league: String, team: String): SanitaryData = {
}