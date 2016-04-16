package term.project.SentimentalStats

trait Formatted {
  def parsedData: List[Array[String]]
}

/**
  * Stores and parses CSV data
  */
case class CSV(rawData: List[String]) extends Formatted {

  /**
    * Parses the data
    */
  lazy val parsedData: List[Array[String]] = {
    for {
      line <- rawData
      fields = line.split(",").map(_.trim)
    } yield fields
  }

  /**
    * Converts parsed data to a list of teams.
    */
  def toListOfTeams: List[Team] = {
    parsedData.map(field => Team(field(0), field(1), field(2), field(3)))
  }
}