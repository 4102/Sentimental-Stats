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

  // each Team in teams has a field:
  // comments: List[Comment] with all the comments referencing them
  // record: String, which is the data from sportsdb in plain JSON
  //
  // Each Comment in comments has two fields: a date and a message body
  // Each Record had a header and a bunch of columns with the numbers in them. The first one is dates of the games.

  // calculateStats(teams)   // sentiment and statistical analysis

  // presentResults()       // somehow
}