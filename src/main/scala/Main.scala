package term.project.SentimentalStats

/**
  * The entry point and main object that executes the program.
  *
  * Extending the App trait is an idiomatic way to define an entry point in Scala.
  *
  * The body of an object extending App will be executed in order,
  * similar to a 'main' method, and also makes any command-line arguments
  * available through an "args" object, type Array[string]
  */
object Main extends App {

  val options = Options.optionsFromArgs(args)
  val teams = Options.getTeams(options)

  val results = WebCrawler.search(teams, options)

  // updateDB(data)         // mongoDB scala driver, presumably

  // calculateStats(data)   // sentiment and statistical analysis

  // updateDB(stats)        // save results to db

  // presentResults()       // somehow
}

/**
  * Representations of teams from different sports/leagues for pattern-matching, etc.
  */
sealed class Team(sport: Symbol, league: Symbol)
case class NCAAMTeam() extends Team('basketball, 'ncaam)
case class NCAAWTeam() extends Team('basketball, 'ncaaw)
case class NBATeam() extends Team('basketball, 'nba)
case class WNBATeam() extends Team('basketball, 'wnba)

