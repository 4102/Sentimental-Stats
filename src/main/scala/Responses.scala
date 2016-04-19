package term.project.SentimentalStats

/**
  * Strings describing program options, state, etc.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
object Responses {
  val arguments = "Use sbt \"run arg1 arg2 etc\" to run with arguments. Enter 'help' for a list of commands."
  val verbose = "verbose: describes program state and actions as it runs. Good for debugging."
  val test = "test: A verbose 'dry run' that doesn't save anything to the database."
  val db = "--db 'true/false': If true, run query teams already in the main database. Defaults to false."
  val file = "--file 'path': run with the queries named in the file of the given path, in CSV format."
  val team = "--team 'teamName' 'teamHome': run, querying only the team given in the arguments."

  lazy val help = {verbose + "\n" + test + "\n" + db + "\n"  + file + "\n" + team + "\n"}
}
