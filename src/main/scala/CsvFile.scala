package term.project.SentimentalStats

/**
  * Retrieves data stored in csv format.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
case class CsvFile(path: String) extends PermanentFile(path) with Csv {

  lazy val data = parse(readLines())

  /**
    * Converts parsed data to a list of teams.
    */
  def readTeams: List[Team] = {
    data.map(field => Team(field(0), field(1), field(2), field(3)))
  }
}