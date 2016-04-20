package term.project.SentimentalStats

import scala.io.Source

/**
  * Handles queries to sportsdatabase.com
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
object SportsDatabase {

  val baseURL = "http://api.sportsdatabase.com/"
  val queryFormat = "/query.json?output=json&sdql="
  val apiKey = "&api_key=guest"

  /**
    * Query api.sportsdatabase.com and parse the results.
    */
  def getRecord(team: Team): Record = {

    val request = getQueryURL(team)
    val response = Source.fromURL(request).mkString
    JsonParser.parseSportsDBRecord(response)
  }

  /**
    * Construct the URL that queries the database.
    */
  private def getQueryURL(team: Team): String = {

    val queryBody = escapeForURL(
      team.league.sport.metrics.mkString(",")
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
    * Percent-encode an raw "SDQL" query for use in URL.
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