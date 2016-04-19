package term.project.SentimentalStats

/**
  * Stores and parses text formatted as simple key-value pairs
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
case class Pairs(rawData: List[String], separator: Char) {

  /**
    * Parses the data as a series of key-value pairs.
    */
  lazy val parsedData: List[Array[String]] = {
    rawData.filter(_.nonEmpty)
      .map(_.split(separator))
  }

  /**
    * Creates a Map from parsed key-value pairs.
    */
  def toMap: Map[String, String] = {

    val keys = parsedData.map(_.head.trim)
    val values = parsedData.map(_.last.trim)

    (keys zip values).toMap
  }
}

/**
  * Companion object for Pairs.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
object Pairs {

  val defaultSeparator = '='

  /**
    * Constructs an instance of Pairs from list of strings.
    */
  def apply(rawData: List[String]): Pairs = {
    new Pairs(rawData, defaultSeparator)
  }
}