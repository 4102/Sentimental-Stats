package term.project.SentimentalStats

import java.time.LocalDate

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.TypeImports
import com.mongodb.casbah.commons.MongoDBList // IntelliJ marks this as unused, but IT IS WRONG

/**
  * Methods and classes for querying the database.
  *
  * Author: Yagnesh Vadalia
  * Last Modified: 4-19-2016
  */
object Database {

    val mongoClient =  MongoClient("localhost", 27017)
    val db = mongoClient("test")
    //println("collection: " + db.collectionNames)
    val coll = db("twitter")

    def countPos(team : String): Int ={

      //var pos = 0
      val emptyCondition = MongoDBObject.empty
      val fields = MongoDBObject("team" -> team,"sent" -> "positive")
      val query = coll.find(fields)
      var pos = query.count()
      pos
    }

    def countNeg(team : String): Int ={

      //var pos = 0
      val emptyCondition = MongoDBObject.empty
      val fields = MongoDBObject("team" -> team,"sent" -> "negative")
      val query = coll.find(fields)
      var neg = query.count()
      neg
    }

    def insertM(team : String, senti : List[(LocalDate,Sentiment.Sentiment)], comments : List[Comment]): TypeImports.WriteResult = {
      val mongoClient =  MongoClient("localhost", 27017)
      val db = mongoClient("test")
      //println("collection: " + db.collectionNames)
      val coll = db("twitter")
      val a = MongoDBObject("team" -> team, "sentiments" -> senti, "comments" -> comments)
      coll.insert( a )
    }


    def statisticsTest(args: Array[String]): Unit = {

      var pos1 = countPos("dallas")
      var neg1 = countNeg("dallas")
      println(pos1)
      println(neg1)
      var prob1: Float = pos1.toFloat/neg1
      println(prob1)

      var pos2 = countPos("Arizona")
      var neg2 = countNeg("Arizona")
      println(pos2)
      println(neg2)
      var prob2: Float = pos2.toFloat/neg2
      println(prob2)

      var win1 = prob1/(prob1+prob2)

      println("winning probability of Dallas :" +win1 )
      println("winning probability of Arizona :" +(1 - win1))
    }
}
