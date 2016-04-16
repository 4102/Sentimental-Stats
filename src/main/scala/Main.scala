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

  val results = WebCrawler.search(teams, options) // returns data from twitter, etc. for sentiment analysis

  // calculateStats(data)   // sentiment and statistical analysis

  // presentResults()       // somehow
}