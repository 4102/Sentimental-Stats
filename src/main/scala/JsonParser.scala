package term.project.SentimentalStats

import play.api.libs.json._
import scala.util.Try

object JsonParser {

  def parseSportsDBRecord(jsonString: String): Record = {

    val json = Json.parse(repairJsonString(jsonString))

    val columnNames = (json \ "headers").as[List[String]]
    val malformedPart = (json \ "groups" \\ "columns").head

    val columns = parseMalformedJson(malformedPart)

    new Record(columnNames, columns)
  }

  /**
    * SportsDatabase is the best and only resource for many stats,
    * but to call the Json they produce malformed is a kindness.
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

  private def repairJsonString(raw: String): String = {
    val start = raw.indexOf('{')
    val end = raw.lastIndexOf('}') + 1

    raw.substring(start, end).replace('\'', '"')
  }
}