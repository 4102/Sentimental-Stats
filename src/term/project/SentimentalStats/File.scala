package term.project.SentimentalStats

import java.io.{BufferedWriter, FileWriter}

import scala.io.Source

/**
  * A class representing a file for IO.
  *
  * Partly just a wrapper for a buffered Java FileWriter; Scala's std lib omits writing to files(!)
  */
class File(path: String, overwrite: Boolean) {

  // Opens a file with the Java FileWriter class, overwriting its contents if false
  val writer = new BufferedWriter(new FileWriter(path, overwrite))

  /**
    * Reads a file, mapping its lines to strings in a list.
    *
    * Doesn't actually use the FileWriter, but for simplicity I'm sticking it here.
    */
  def readLines(): List[String] = {

    val lines = Source.fromFile(path).getLines.toList
    return lines
  }

  /**
    * Writes the elements of a list to a file, separated by newlines.
    */
  def writeLines(lines: List[Any]): Unit = {

    writer.write(lines.mkString("\n"))
  }

  /**
    * Writes a string to a file.
    */
  def writeLine(line: String): Unit = {

    writer.write(line + "\n")
  }

  /**
    * Close FileWriter
    */
  def close(): Unit = {
    writer.close()
  }

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

    val lines = readLines()

    val keys = for {
      line <- lines
      after = line.indexOf("=")
      key = line.substring(after).trim // remove extraneous whitespace
    } yield key

    return keys
  }

}

/**
  * Companion object to File, contains useful File-related functions/"static methods".
  */
object File {

}