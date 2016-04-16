package term.project.SentimentalStats

import scala.annotation.tailrec
import scala.sys._

/**
  * Strings describing program options, state, etc.
  */
object Messages {
  val arguments = "Use sbt \"run arg1 arg2 etc\" to run with arguments. Enter 'help' for a list of commands."
  val verbose = "verbose: describes program state and actions as it runs. Good for debugging."
  val test = "test: A verbose 'dry run' that doesn't save anything to the database."
  val db = "--db 'true/false': If true, run query teams already in the main database. Defaults to false."
  val file = "--file 'path': run with the queries named in the file of the given path, in CSV format."
  val team = "--team 'teamName' 'teamHome': run, querying only the team given in the arguments."

  lazy val help = {verbose + "\n" + test + "\n" + db + "\n"  + file + "\n" + team + "\n"}
}


/**
  * Options for running the project.
  */
object Options {

  /**
    * Read, interpret and print interpretation of any command-line arguments.
    */
  def optionsFromArgs(args: Array[String]): Map[String, String] = {

    if (args.isEmpty) println("No arguments. " + Messages.arguments)

    matchArgs(Map(), args.toList)
  }
  
  private object Key {
    val help = "help"
    val verbose = "verbose"
    val test = "test"
    val db = "--db"
    val file = "--file"
    val team = "--team"
  }

  /**
    * Matches command-line arguments to valid options and stores them as a collection of key-value pairs.
    */
  @tailrec private def matchArgs(options: Map[String, String], args: List[String]): Map[String, String] = {

    args match {

      case Nil => options // empty List and end of recursion.

      case Key.help :: tail =>
        println(Messages.help)
        exit(0)

      case Key.verbose :: tail =>
        println("Verbose mode.")
        matchArgs(options ++ Map(Key.verbose -> "true"), tail)

      case Key.test :: tail =>
        println("Test only, nothing will be committed.")
        matchArgs(options ++ Map(Key.test -> "true"), tail)

      case Key.db :: bool :: tail =>
        println("Use Database: " + bool)
        matchArgs(options ++ Map(Key.db -> bool), tail)

      case Key.file :: path :: tail =>
        println("CSV file path: " + path)
        matchArgs(options ++ Map(Key.file -> path), tail)

      case Key.team :: teamName :: teamHome :: tail =>
        println("Querying team: " + teamName + "/" + teamHome)
        matchArgs(options ++ Map(Key.team -> {teamName + "," + teamHome}), tail)

      case _ =>
        error(args.mkString("Invalid or malformed argument: ", ", ", "."))
        println(Messages.arguments)
        exit(1)
    }
  }

  /**
    * Assemble a list of sports teams from sources depending on user options.
    */
  def getTeams(opt: Map[String, String]): List[Team] = {

    val fileTeams = opt.get(Messages.file)
      .map(new CSVFile(_).data.toListOfTeams)
      .getOrElse(Nil)

    // val dbTeams = opt.get(dbKey).map(new DB.teamsFromDB).getOrElse(Nil) // implement
    val dbTeams = if (opt.contains(Messages.db)) Nil else Nil

    val manualTeam = opt.get(Key.team) match {
      case Some(team) =>
        val fields = team.split(",")
        List(Team(fields.head, fields.tail.head))
      case None => Nil
    }

    val allOptions = (fileTeams ::: dbTeams ::: manualTeam).distinct
    val default = new CSVFile("teams.csv").data.toListOfTeams

    if (allOptions.isEmpty) default else allOptions

  }
}