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

  val options: Map[Symbol, Any] = Options.getFromArgs(args)

  val teamName = "Tarheels"
  val teamHome = "Chapel Hill"

  val results = WebCrawler.search(teamName, teamHome)

  // updateDB(data)         // errors here scuttle everything

  // calculateStats(data)   // sentiment and statistical analysis

  // updateDB(stats)        // save results to db

  // presentResults()       // somehow

}

/**
  * 
  */
class Team(name: String, home: String)

sealed abstract class TeamID(sport: Symbol, league: Symbol) extends Team(String, String)
case class NCAAMTeam() extends TeamID('basketball, 'ncaam)
case class NCAAWTeam() extends TeamID('basketball, 'ncaaw)
case class NBATeam() extends TeamID('basketball, 'nba)
case class WNBATeam() extends TeamID('basketball, 'wnba)

