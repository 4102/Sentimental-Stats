package term.project.SentimentalStats

import java.time.LocalDate

import com.mongodb.casbah.Imports._
import term.project.SentimentalStats.Sentiment.Sentiment

/**
  * The entry point and main object that executes the program.
  *
  * Extending the App trait is an idiomatic way to define an entry point in Scala.
  *
  * The body of an object extending App will be executed in order,
  * similar to a 'main' method, and also makes any command-line arguments
  * available through an "args" object, type Array[string]
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
object Main extends App with Options with Graphing {

  val options = Console.optionsFromArgs(args)

  val mongoClient =  MongoClient("localhost", 27017)
  val db = mongoClient("test")

  val teams = getTeams(options)

  teams.head.sentimentRecord.foreach(println(_))

  graphAll(teams, options)


}