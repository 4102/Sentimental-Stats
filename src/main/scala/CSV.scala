package term.project.SentimentalStats


/**
  * Stores and parses CSV data
  */
case class CSV(rawData: List[String]) {

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