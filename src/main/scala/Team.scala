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

  val record: Record = SportsDatabase.getRecord(this)
  val seasonInterval: Interval = record.getSeasonInterval
  val comments: List[Comment] = Twitter.searchInterval(name, seasonInterval)
}

object Team {

  /**
    * Constructs a Team.
    */
  def apply(
      name: String,
      home: String,
      league: League,
      season: Int): Team = {

    new Team(name, home, league, season)
  }

  /**
    * Constructs a team from string for every field..
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