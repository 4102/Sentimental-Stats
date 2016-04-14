package term.project.SentimentalStats


trait Parser

/**
  * Parses CSV data
  */
trait SimpleCSVParser extends Parser {

  /**
    * Parses lines of csv
    */
  def parse(lines: List[String]): List[Array[String]] = {

    for {
      line <- lines
      fields = line.split(",").map(_.trim)
    } yield fields.toArray
  }
}

/**
  * Parses strings formatted at key-value pairs
  */
trait KeyValueParser extends Parser {

  val separator = '='

  private def getPairs(list: List[String]): List[Array[String]] = {
    list.filter(_.nonEmpty).map(_.split(separator))
  }

  def getKeys(list: List[String]): List[String] = {
    getPairs(list).map(_.head.trim)
  }

  def getValues(list: List[String]): List[String] = {
    getPairs(list).map(_.last.trim)
  }

  // No point using symbols when they are created at runtime.
  def parse(pairs: List[String]): Map[String, String] = {
    (getKeys(pairs) zip getValues(pairs)).toMap
  }
}