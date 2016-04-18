package term.project.SentimentalStats

/**
  * Executes based on options interpreted from the console.
  */
trait Options {

  /**
    * Assemble a list of sports teams from sources depending on user options.
    */
  def getTeams(options: Map[String, String]): List[Team] = {

    val fileTeams = options.get(OptionKey.file)
      .map(new CSVFile(_).data.toListOfTeams)
      .getOrElse(Nil)

    // val dbTeams = opt.get(dbKey).map(new DB.teamsFromDB).getOrElse(Nil) // implement
    val dbTeams = if (options.contains(Responses.db)) Nil else Nil

    val manualTeam = options.get(OptionKey.team) match {
      case Some(team) =>
        val fields = team.split(",")
        List(Team(fields.head, fields(1), fields(2), fields(3)))
      case None => Nil
    }

    val allOptions = (fileTeams ::: dbTeams ::: manualTeam).distinct
    val default = new CSVFile("teams.csv").data.toListOfTeams

    if (allOptions.isEmpty) default else allOptions
  }
}