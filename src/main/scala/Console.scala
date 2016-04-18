package term.project.SentimentalStats

import scala.annotation.tailrec
import scala.sys._

/**
  * Interprets and executes command-line arguments.
  */
object Console {

  /**
    * Read and interpret command-line arguments.
    */
  def optionsFromArgs(args: Array[String]): Map[String, String] = {

    if (args.isEmpty) println("No arguments. " + Responses.arguments)
    Console.matchArgs(Map(), args.toList)
  }

  /**
    * Matches command-line arguments to valid options and stores them as a collection of key-value pairs.
    */
  @tailrec
  def matchArgs(options: Map[String, String], args: List[String]): Map[String, String] = {

    args match {

      case Nil => options // empty List and end of recursion.

      case OptionKey.help :: tail =>
        println(Responses.help)
        exit(0)

      case OptionKey.verbose :: tail =>
        println("Verbose mode.")
        matchArgs(options ++ Map(OptionKey.verbose -> "true"), tail)

      case OptionKey.test :: tail =>
        println("Test only, nothing will be committed.")
        matchArgs(options ++ Map(OptionKey.test -> "true"), tail)

      case OptionKey.db :: bool :: tail =>
        println("Use Database: " + bool)
        matchArgs(options ++ Map(OptionKey.db -> bool), tail)

      case OptionKey.file :: path :: tail =>
        println("CSV file path: " + path)
        matchArgs(options ++ Map(OptionKey.file -> path), tail)

      case OptionKey.team :: teamName :: teamHome :: teamLeague :: teamSeason :: tail =>
        println("Querying team: " + teamName + "/" + teamHome)
        matchArgs(options ++ Map(OptionKey.team -> {teamName + "," + teamHome}), tail)

      case _ =>
        error(args.mkString("Invalid or malformed argument: ", ", ", "."))
        println(Responses.arguments)
        exit(1)
    }
  }
}
