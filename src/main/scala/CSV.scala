package term.project.SentimentalStats


/**
  * Stores and parses CSV data
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
trait Csv {

  /**
    * Parses CSV to a collection; assumes one record per line.
    */
  def parse(csv: List[String]): List[Array[String]] = {
    for {
      line <- csv
      fields = line.split(",").map(_.trim)
    } yield fields
  }
}