package term.project.SentimentalStats

import term.project.SentimentalStats.FilePaths._

/**
  * Contains keys and configuration options.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
case object ConfigFile extends PermanentFile(settingsDir + "config.txt") {

  lazy val data = Pairs(readLines())
}
