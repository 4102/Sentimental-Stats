/**
  * Sentiments represented by the output of the sentiment analysis.
  *
  * Author: Yagnesh
  * Last Modified: 4/13/2016
  */
object Sentiment extends Enumeration {
  type Sentiment = Value
  val POSITIVE, NEGATIVE, NEUTRAL = Value

  def toSentiment(sentiment: Int): Sentiment = sentiment match {
    case x if x == 0 || x == 1 => Sentiment.NEGATIVE
    case 2 => Sentiment.NEUTRAL
    case x if x == 3 || x == 4 => Sentiment.POSITIVE
  }
}
