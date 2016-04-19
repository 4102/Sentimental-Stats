package term.project.SentimentalStats

/**
  * Stores raw textual data for easy inspection.
  *
  * Author: David Prichard
  * Last Modified: 4-19-2016
  */
case object SampleFile extends TemporaryFile("sample.txt") {

  /**
    * Write n elements of a list to file, prefaced by a description.
    */
  def sampleN(description: String, data: List[String], n: Int): Unit = {

    writer.write(description + "\n")
    writeLines(data.take(n))
    flush()
  }

  /**
    * Write a string to file, prefaced by a description.
    */
  def sample(description: String, data: String): Unit = {

    writer.write(description + "\n" + data)
    flush()
  }
}
