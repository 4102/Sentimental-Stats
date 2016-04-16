package term.project.SentimentalStats

import scala.io.Source
import term.project.SentimentalStats.Sport._

/**
  * Handles queries to sportsdatabase.com
  */
object SportsDatabase {

  val baseURL = "http://api.sportsdatabase.com/"
  val inputFormat = "/query.json?sdql="
  val outputFormat = "&output=json&api_key=guest"

  def getStatsFor(teams: List[Team]): List[String] = {

    def query(team: Team): String = {
      val request = escape(getURL(team))
      Source.fromURL(request).mkString
    }

    teams.map(team => query(team))
  }

  private def getURL(team: Team): String = {

    ( baseURL
      + team.league.name
      + inputFormat
      + team.stats.mkString(",")
      + "@team=" + team.name
      + " and season=" + team.season
      + outputFormat
      )
  }

  /**
    * Percent-encode an SDQL query for use in a URL
    */
  private def escape(rawURL: String): String = {

    object Encoding {
      val comma = "%2C"
      val at = "%40"
      val equals = "%3D"
      val space = "%20"
    }

    rawURL.replaceAll(",", Encoding.comma)
      .replaceAll("@", Encoding.at)
      .replaceAll("=", Encoding.equals)
      .replaceAll(" ", Encoding.space)
  }
}
