package term.project.SentimentalStats

import scala.annotation.tailrec
import scala.sys._

/**
  * Strings describing program options, state, errors, etc.
  */
trait ConsoleMessages {

  val argumentsMessage = "Use sbt \"run arg1 arg2 etc\" to run with arguments. Enter 'help' for a list of commands."

  val verboseMessage = "verbose: describes program state and actions as it runs. Good for debugging."
  val testMessage = "test: A verbose 'dry run' that doesn't save anything to the database."
  val dbMessage = "--db 'true/false': If true, run query teams already in the main database. Defaults to false."
  val fileMessage = "--file 'path': run with the queries named in the file of the given path, in CSV format."
  val teamMessage = "--team 'teamName' 'teamHome': run, querying only the team given in the arguments."

  val helpMessage = {
    verboseMessage + "\n" + testMessage + "\n" + dbMessage + "\n"  +fileMessage + "\n" + teamMessage + "\n"
  }
}

/**
  * Symbols for use as keys in key/value pairs representing options.
  */
trait Keys {
  val verboseKey = 'verbose
  val testKey = 'test
  val dbKey = 'db
  val fileKey = 'file
  val teamKey = 'team
}

/**
  * Options for running the project.
  */
object Options extends ConsoleMessages with Keys {

  /**
    * Read, interpret and print interpretation of any command-line arguments.
    */
  def optionsFromArgs(args: Array[String]): Map[Symbol, String] = {

    if (args.isEmpty) println("No arguments. " + argumentsMessage)

    matchArgs(Map(), args.toList)
  }

  /**
    * Matches command-line arguments to valid options and stores them as a collection of key-value pairs.
    */
  @tailrec def matchArgs(options: Map[Symbol, String], args: List[String]): Map[Symbol, String] = {

    args match {

      case Nil => options // empty List and end of recursion.

      case "help" :: tail =>
        println(helpMessage)
        exit(0)

      case "verbose" :: tail =>
        println("Verbose mode.")
        matchArgs(options ++ Map(verboseKey -> "true"), tail)

      case "test" :: tail =>
        println("Test only, nothing will be committed.")
        matchArgs(options ++ Map(testKey -> "true"), tail)

      case "--db" :: bool :: tail =>
        println("Use Database: " + bool)
        matchArgs(options ++ Map(dbKey -> bool), tail)

      case "--file" :: path :: tail =>
        println("CSV file path: " + path)
        matchArgs(options ++ Map(fileKey -> path), tail)

      case "--team" :: teamName :: teamHome :: tail =>
        println("Querying team: " + teamName + "/" + teamHome)
        matchArgs(options ++ Map(teamKey -> {
          teamName + "," + teamHome
        }), tail)

      case _ =>
        error(args.mkString("Invalid or malformed argument: ", ", ", "."))
        println(argumentsMessage)
        exit(1)
    }
  }

  /**
    * Assemble a list of sports teams from sources depending on user options.
    */
  def getTeams(opt: Map[Symbol, String]): List[Team] = {

    val fileTeams = opt.get(fileKey).map(new CSVFile(_).readTeams()).getOrElse(Nil)

    // val dbTeams = opt.get(dbKey).map(new DB.teamsFromDB).getOrElse(Nil) // implement
    val dbTeams = if (opt.contains(dbKey)) Nil else Nil

    val manualTeam = opt.get(teamKey) match {
      case Some(team) =>
        val fields = team.split(",")
        List(new Team(fields.head, fields(1)))
      case None => Nil
    }

    val allOptions = (fileTeams ::: dbTeams ::: manualTeam).distinct
    val default = new CSVFile("teams.csv").readTeams()

    if (allOptions.isEmpty) default else allOptions

  }
}