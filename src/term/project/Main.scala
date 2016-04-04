package term.project

/**
  * The entry point and main object that executes the program.
  */

/* Extending the App trait is a common way to define an entry point in Scala.
 *
 * The statements in the body of an object extending App will be executed in order,
 * similar to a 'main' method in Java, and also makes any command-line arguments
 * available through an "args" object, type Array[string]
 */
object Main extends App {

  // Prints application name and any command-line arguments.
  println("Sentimental Stats")
  println("Arguments: " + (args mkString ", "))

  /*
  for {
    sport <- sports
    league <- sport
    team <- league
  } yield {
    new Tuple3(sport, league, team);
  }
  */

  // val crawler = new WebCrawler
  // crawler.collectData(sport : String, league : String, team : String) : data

  // filterData(data)       // escaping, filtering, deduplication etc.

  // logData(data)          // write to log file for debugging

  // updateDB(data)         // errors here scuttle everything

  // getStats(data) : stats //

  // updateDB(stats)        //

}

