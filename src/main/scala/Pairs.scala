package term.project.SentimentalStats

/**
  * Stores and parses text formatted as key-value pairs
  */
case class Pairs(rawData: List[String], separator: Char) extends Formatted {

  /**
    * Parses the data
    */
  lazy val parsedData: List[Array[String]] = {
    rawData.filter(_.nonEmpty)
      .map(_.split(separator))
  }

  def toMap: Map[String, String] = {

    val keys = parsedData.map(_.head.trim)
    val values = parsedData.map(_.last.trim)

    (keys zip values).toMap
  }

}

object Pairs {

  val defaultSeparator = '='

  def apply(rawData: List[String]): Pairs = {
    new Pairs(rawData, defaultSeparator)
  }
}