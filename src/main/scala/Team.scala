package term.project.SentimentalStats

import term.project.SentimentalStats.League._

/**
  *
  */
trait Roster {
  //def roster // players on the team
  //def players //players on the team that register minutes
}

/**
  *
  */
trait Coaches {
  //def headCoaches // head coaches at any point during season. Usually just one.
}

/**
  * Team for a particular season.
  */
class Team(
    val name: String,
    val home: String,
    val league: League,
    val season: Int)
  extends Roster
  with Coaches {

  def stats = league.sport.stats
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
    * Constructor overload that takes strings for each param.
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