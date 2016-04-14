package term.project.SentimentalStats

import java.io.{BufferedWriter, FileWriter}

import scala.io.Source

/**
  * A class representing a file for IO operations.
  *
  * Partly just a wrapper for a buffered Java FileWriter; Scala's std lib omits writing to files(!)
  * @param append overwrite if false, append if true.
  */
abstract class File(path: String, append: Boolean) {

  val writer = new BufferedWriter(new FileWriter(path, append))

  /**
    * Reads lines of a file to a list of strings.
    */
  def lines(): List[String] =  Source.fromFile(path).getLines.toList

  /**
    * Writes elements of a list to lines of a file.
    */
  def writeLines(lines: List[Any]): Unit = {

    writer.write(lines.mkString("\n"))
    writer.flush()
  }

  def close(): Unit = writer.close()
}

/**
  * Retrieves data stored in csv format.
  */
case class CSVFile(path: String)
  extends File(path.toString, true)
  with SimpleCSVParser {

  def readTeams(): List[Team] = {
    parse(lines()).map(x => new Team(x.head, x.last))
  }
}

/**
  * Stores raw textual data for easy inspection.
  */
case object DataFile extends File("dataSample.txt", false) {

  /**
    * Write n elements of a list to file, prefaced by a description.
    */
  def sample(description: String, data: List[Any], n: Int): Unit = {

    writer.write(description + "\n")
    writeLines(data.take(n))
  }
}

/**
  * Contains keys and configuration options.
  */
case object ConfigFile
  extends File("config.txt", true)
    with KeyValueParser {

  def extractKeys(): List[String] = {
    getKeys(lines())
  }

  def extractValues(): List[String] = {
    getValues(lines())
  }
}