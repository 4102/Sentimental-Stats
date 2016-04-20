package term.project.SentimentalStats

import term.project.SentimentalStats.League._
import term.project.SentimentalStats.SentimentAnalyzer._

/**
  * A team over a particular season.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
class Team(
    val name: String,
    val home: String,
    val league: League,
    val seasonYear: Int)
  extends Time {

  val record: Record = SportsDatabase.getRecord(this)

  val comments: List[Comment] = Twitter.searchInterval(name, record.seasonInterval)

  val sentimentRecord = for {
    comment <- comments
    sentiment = mainSentiment(comment.text)
  } yield Tuple2(comment.time, sentiment)
}

/**
  * Companion object for team.
  */
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
    * Constructs a team when passed a string for every parameter.
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