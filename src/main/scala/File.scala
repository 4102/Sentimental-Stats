package term.project.SentimentalStats

import java.io.{BufferedWriter, FileWriter}

import scala.io.Source

/**
  * A class representing a file for IO.
  *
  * Partly just a wrapper for a buffered Java FileWriter; Scala's std lib omits writing to files(!)
  */
class File(path: String, append: Boolean) {

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

  /**
    * Write first n elements of a list to log file, prefaced by a description.
    */
  def sample(desc: String, data: List[Any], n: Int): Unit = {

    writer.write(desc + "\n")
    writeLines(data.take(n))
  }

  /**
    * Extract a list of keys from a file
    */
  def extractKeys(): List[String] = {

    val delimiter = "="

    val keys = for {
      line <- readLines()
      if line.contains(delimiter)
    } yield line.drop(line.indexOf(delimiter) + 1).trim // get substring after "=" and toss whitespace.

    println("Twitter Authorization Keys: " + keys.mkString(", "))

    return keys
  }
}