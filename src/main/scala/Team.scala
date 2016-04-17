package term.project.SentimentalStats

import term.project.SentimentalStats.League._

/**
  * Team for a particular season.
  */
class Team(
    val name: String,
    val home: String,
    val league: League,
    val seasonYear: Int) {

  val record = SportsDatabase.getRecord(this)
  val seasonInterval = seasonYear.toString + "-10-01" + "," + {seasonYear + 1}.toString + "-05-01"
  val comments = Twitter.searchOverPeriod(name, seasonInterval)

  SampleFile.sample("JSON from SportsDatabase.com: ", record)
}

object Team {

  /**
    * Constructor for Team
    */
  def apply(
      name: String,
      home: String,
      league: League,
      season: Int): Team = {

    new Team(name, home, league, season)
  }

  /**
    * Constructor overload that accepts strings for every parameter.
    */
  def apply(
      name: String,
      home: String,
      leagueString: String,
      seasonString: String): Team = {

    val league = League.stringToLeague(leagueString)
    val season = seasonString.toInt
    new Team(name, home, league, season)
  }
}