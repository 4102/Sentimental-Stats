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
object Main extends App with Options {

  val options = Console.optionsFromArgs(args)

  val teams = getTeams(options)



  // each Team in teams has a field:
  // comments: List[Comment] with all the comments referencing them
  // record: with field statNames, a list of the stats tracked, and stat values, an array
  // seasonInterval
  // Each Comment in comments has two fields: a date and a message body

  // calculateStats(teams)   // sentiment and statistical analysis

  // presentResults()       // somehow
}