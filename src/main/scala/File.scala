package term.project.SentimentalStats

import java.io.{BufferedWriter, FileWriter}

import scala.io.Source

/**
  * A class representing a file for IO operations.
  *
  * Partly just a wrapper for a buffered Java FileWriter; Scala's std lib omits writing to files(!)
  */
abstract class File(path: String, append: Boolean) {

  // Opens a file with the Java FileWriter class, overwriting its contents if false
  val writer = new BufferedWriter(new FileWriter(path, append))

  /**
    * Reads a file, mapping its lines to strings in a list.
    */
  def readLines(): List[String] =  Source.fromFile(path).getLines.toList

  /**
    * Writes the elements of a list to a file, separated by newlines.
    */
  def writeLines(lines: List[Any]): Unit = {

    writer.write(lines.mkString("\n"))
    writer.flush()
  }

  /**
    * Writes a string to a file.
    */
  def writeLine(line: String): Unit = {

    writer.write(line + "\n")
    writer.flush()
  }

  /**
    * Close FileWriter
    */
  def close(): Unit = writer.close()
}

/**
  * File containing csv data.
  *
  * Format is assumed to be one record per line with each field separated by a comma.
  */
class CSVFile(path: String) extends File(path.toString, true) {

  /**
    * Extracts team data from a csv file.
    *
    * Interprets each line as a record with two fields: the team's name and home.
    */
  def teamsFromCSV: List[Team] = {

    for {
      line <- readLines()
      fields = line.split(",").map(_.trim)
    } yield new Team(fields.head, fields(1))
  }
}

/**
  * File for storing text samples of collected data for easy inspection.
  */
object DataFile extends File("dataSample.txt", false) {

  /**
    * Write first n elements of a list to log file, prefaced by a description.
    */
  def sample(desc: String, data: List[Any], n: Int): Unit = {

    writer.write(desc + "\n")
    writeLines(data.take(n))
  }
}

/**
  * File containing keys and configuration options.
  *
  * Format is assumed to one key/value pair per line, separated by an '='.
  */
object KeyFile extends File("twitterConfig.txt", true) {

  /**
    * Extract a list of keys from a file
    */
  def extractKeys(): List[String] = {

    val delimiter = '='

    for {
      line <- readLines()
      if line.contains(delimiter)
      key = line.drop(line.indexOf(delimiter) + 1)
    } yield key.trim
  }
}