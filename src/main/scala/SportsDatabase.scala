package term.project.SentimentalStats

import scala.io.Source

/**
  * Handles queries to sportsdatabase.com
  */
object SportsDatabase {

  val baseURL = "http://api.sportsdatabase.com/"
  val queryFormat = "/query?output=json&sdql=" // default, json, what else?
  val apiKey = "&api_key=guest"

  def getStatsFor(teams: List[Team]): List[String] = {

    def singleQuery(team: Team): String = {
      val request = getQueryURL(team)
      println(request)
      Source.fromURL(request).mkString
    }

    teams.map(team => singleQuery(team))
  }

  private def getQueryURL(team: Team): String = {

    val queryBody = escapeForURL(
      team.stats.mkString(",")
        + "@team="
        + team.name
        + " and season="
        + team.season
    )

    ( baseURL
      + team.league.name
      + queryFormat
      + queryBody
      + apiKey
      )
  }

  /**
    * Percent-encode an SDQL query for use in URL
    */
  private def escapeForURL(raw: String): String = {

    object Encoding {
      val comma = "%2C"
      val at = "%40"
      val equals = "%3D"
      val space = "%20"
    }

    raw.replaceAll(",", Encoding.comma)
      .replaceAll("@", Encoding.at)
      .replaceAll("=", Encoding.equals)
      .replaceAll(" ", Encoding.space)
  }
}
