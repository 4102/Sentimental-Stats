package term.project.SentimentalStats

import java.io.{BufferedWriter, FileWriter}
import scala.io.Source
import term.project.SentimentalStats.FilePaths._

/**
  * A class representing a file for IO operations.
  *
  * Wraps a buffered Java FileWriter for writing, which Scala's std lib omits.
  *
  * @param append overwrite file if false, append if true.
  */
abstract class File(path: String, append: Boolean) {

  val writer = new BufferedWriter(new FileWriter(path, append))

  /**
    * Reads lines of a file to strings in a list.
    */
  def readLines(): List[String] =  Source.fromFile(path).getLines.toList

  /**
    * Writes elements of a list to lines in a file.
    */
  def writeLines(lines: Traversable[Any]): Unit = {

    writer.write(lines.mkString("\n"))
  }

  /**
    * Flushes the buffer.
    */
  def flush(): Unit = writer.flush()

  /**
    * Closes the file.
    */
  def close(): Unit = writer.close()
}

class PermanentFile(path: String) extends File(path, true)
class TemporaryFile(path: String) extends File(path, false)


/**
  * Retrieves data stored in csv format.
  */
case class CSVFile(path: String) extends PermanentFile(path) {

  val data = CSV(readLines())
}

case class StatsFile(fileName: String) extends PermanentFile(settingsDir + fileName)

/**
  * Contains keys and configuration options.
  */
case object ConfigFile extends PermanentFile(settingsDir + "config.txt") {

  lazy val data = Pairs(readLines())
}

/**
  * Stores raw textual data for easy inspection.
  */
case object SampleFile extends TemporaryFile("sample.txt") {

  /**
    * Write n elements of a list to file, prefaced by a description.
    */
  def sampleN(description: String, data: List[String], n: Int): Unit = {

    writer.write(description + "\n")
    writeLines(data.take(n))
    flush()
  }

  /**
    * Write a string to file, prefaced by a description.
    */
  def sample(description: String, data: String): Unit = {

    writer.write(description + "\n" + data)
    flush()
  }
}
