package term.project.SentimentalStats


/**
  * Stores and parses CSV data
  */
trait Csv {

  /**
    * Parses the data
    */
  def parse(csv: List[String]): List[Array[String]] = {
    for {
      line <- csv
      fields = line.split(",").map(_.trim)
    } yield fields
  }
}