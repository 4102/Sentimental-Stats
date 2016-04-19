package term.project.SentimentalStats

import play.api.libs.json._
import scala.util.Try

/**
  * Parses Json, including the malformed output from sportsdatabase.com
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
object JsonParser {

  /**
    * Parse the Json from sportsdatabase as a Record.
    */
  def parseSportsDBRecord(jsonString: String): Record = {

    val json = Json.parse(repairJsonString(jsonString))
    val columnNames = (json \ "headers").as[List[String]]

    val malformedPart = (json \ "groups" \\ "columns").head
    val columns = parseMalformedJson(malformedPart)

    new Record(columnNames, columns)
  }

  /**
    * Parse the malformed Json from sportsdatabase.com
    */
  private def parseMalformedJson(json: JsValue): Array[Array[Option[Double]]] = {

    val array = json.toString
      .drop(1).dropRight(1)
      .replace("],","]$")
      .split('$')

    for {
      sequence <- array
      parsed = sequence.drop(1).dropRight(1)
        .split(',')
        .map(s => Try(s.toDouble).toOption)
    } yield parsed
  }

  /**
    * Correct the less-malformed Json output from sportsdatabase.com,
    * so that it can be parsed as Json.
    */
  private def repairJsonString(raw: String): String = {
    val start = raw.indexOf('{')
    val end = raw.lastIndexOf('}') + 1

    raw.substring(start, end).replace('\'', '"')
  }
}