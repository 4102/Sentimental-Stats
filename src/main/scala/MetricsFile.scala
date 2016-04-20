package term.project.SentimentalStats

import term.project.SentimentalStats.FilePaths._

/**
  * File containing the stats we are interested in.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
case class MetricsFile(fileName: String) extends PermanentFile(settingsDir + fileName)
