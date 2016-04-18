package term.project.SentimentalStats

import scala.io.Source

/**
  * Handles queries to sportsdatabase.com
  */
object SportsDatabase {

  val baseURL = "http://api.sportsdatabase.com/"
  val queryFormat = "/query.json?output=json&sdql="
  val apiKey = "&api_key=guest"

  def getRecord(team: Team): Record = {

    val request = getQueryURL(team)
    val response = Source.fromURL(request).mkString
    JsonParser.parseSportsDBRecord(response)
  }

  private def getQueryURL(team: Team): String = {

    val queryBody = escapeForURL(
      team.league.sport.stats.mkString(",")
        + "@team="
        + team.name
        + " and season="
        + team.seasonYear
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

    raw.replace(",", Encoding.comma)
      .replace("@", Encoding.at)
      .replace("=", Encoding.equals)
      .replace(" ", Encoding.space)
  }
}