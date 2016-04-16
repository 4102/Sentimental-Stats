package term.project.SentimentalStats

import java.io.{BufferedWriter, FileWriter}
import scala.io.Source

/**
  * A class representing a file for IO operations.
  *
  * Wraps a buffered Java FileWriter for writing, which Scala's std lib omits.
  *
  * @param append overwrite file if false, append if true.
  */
class File(path: String, append: Boolean) {

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

/**
  * Retrieves data stored in csv format.
  */
class CSVFile(path: String) extends File(path, true) {

  val data = CSV(readLines())

}

/**
  * Contains keys and configuration options.
  */
case object ConfigFile extends File("config.txt", true) {

  lazy val data = Pairs(readLines())
}

/**
  * Stores raw textual data for easy inspection.
  */
case object SampleFile extends File("sample.txt", false) {

  /**
    * Write n elements of a list to file, prefaced by a description.
    */
  def sample(description: String, data: List[Any], n: Int): Unit = {

    writer.write(description + "\n")
    writeLines(data.take(n))
    flush()
  }
}
