package term.project.SentimentalStats

import java.io.{BufferedWriter, FileWriter}
import scala.io.Source

/**
  * A class representing a file for IO operations.
  * Wraps a buffered Java FileWriter for writing, which Scala's std lib omits.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
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

/**
  * Files representing permanent (non-overwritable) and temporary (overwritable) files.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
class PermanentFile(path: String) extends File(path, true)
class TemporaryFile(path: String) extends File(path, false)