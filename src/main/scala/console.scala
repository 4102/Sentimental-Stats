package term.project.SentimentalStats

import scala.annotation.tailrec
import scala.sys.exit

/**
  * Miscellaneous strings describing program options, state, errors, etc.
  */
trait ConsoleMessages {

  val argumentsMessage = "Use sbt \"run arg1 arg2 etc\" to run with arguments. Enter 'help' for a list of commands."

  val defaultMessage = "default: run with default arguments and parameters. Same as no arguments."
  val testMessage = "test: A verbose 'dry run' that doesn't save anything to the database."
  val fileMessage = "-file 'path': run with the queries named in the file of the given path, in CSV format."
  val helpMessage = {defaultMessage + "\n" + testMessage + "\n"  +fileMessage + "\n"}
}

/**
  * Options for running the project.
  */
object Options extends ConsoleMessages {

  /**
    * Read, interpret and print interpretation of any command-line arguments.
    */
  def getFromArgs(args: Array[String]): Map[Symbol, Any] = {

    println("\n Arguments: ")
    if (args.isEmpty) println("None." + argumentsMessage)

    val options = matchArgs(Map(), args.toList)
    return options
  }

  /**
    * Matches command-line arguments to valid options and stores them as a collection of key-value pairs.
    *
    * Pattern matching over linked lists is a common Scala idiom, and basically requires tail recursion.
    */
  @tailrec def matchArgs(options : Map[Symbol, Any], args: List[String]): Map[Symbol, Any] = {

    args match {
      case Nil => options // empty List and end of recursion.

      case "help" :: tail =>
        println(helpMessage)
        exit(0)

      case "default" :: tail =>
        println(defaultMessage)
        matchArgs(options ++ Map('default -> true), tail)

      case "test" :: tail =>
        println(testMessage)
        matchArgs(options ++ Map('test -> true), tail)

      case "-file" :: path :: tail =>
        println(fileMessage)
        println("File path: " + path)
        matchArgs(options ++ Map('file -> path), tail)

      case _ =>
        println(args.mkString("Invalid arguments: ", ", ", "."))
        println(argumentsMessage)
        exit(1)
    }
  }
}