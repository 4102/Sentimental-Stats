package term.project.SentimentalStats

/**
  * Unfortunately, the libraries and APIs we use all have different date formats.
  * Here are case classes representing some formats, for proper conversions.
  */

/**
  * Year formatted YYYY. Needed to represent season and query sportsdatabase.com
  */
case class YYYY(year: Int) {
  require( year<10000 & year>999 ) // year should have 4 digits
}

/**
  * Date formatted YYYYMMDD. Format returned from sportsdatabase.com queries.
  */
case class YYYYMMDD(date: Int) {

}
