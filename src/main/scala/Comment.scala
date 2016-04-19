package term.project.SentimentalStats

import java.time.LocalDate

/**
  * A comment from any source.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
case class Comment(time: LocalDate, text: String)