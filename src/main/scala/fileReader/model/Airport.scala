package fileReader.model

import scala.util.Try

case class Airport(id: Int,
                   ident: String,
                   _type: String,
                   name: String,
                   latitude_deg: Double,
                   longitude_deg: Double,
                   elevation_ft: Double,
                   continent: String,
                   iso_country: String,
                   iso_region: String,
                   municipality: String,
                   scheduled_service: Option[String] = None,
                   gps_code: Option[String] = None,
                   iata_code: Option[String] = None,
                   local_code: Option[String] = None,
                   home_link: Option[String] = None,
                   wikipedia_link: Option[String] = None,
                   keywords: Option[String] = None)

object Airport {
  def fromCsvLine(line: Array[String]): Option[Airport] = {
    parseAirport(line)
  }

  def parseAirport(line: Array[String]): Option[Airport] = {
    Try(Some(Airport(
      line(0).toInt,
      line(1),
      line(2),
      line(3),
      line(4).toDouble,
      line(5).toDouble,
      line(6).toDouble,
      line(7),
      line(8),
      line(9),
      line(10),
      Try(Some(line(11))) getOrElse None,
      Try(Some(line(12))) getOrElse None,
      Try(Some(line(13))) getOrElse None,
      Try(Some(line(14))) getOrElse None,
      Try(Some(line(15))) getOrElse None,
      Try(Some(line(16))) getOrElse None,
      Try(Some(line(17))) getOrElse None
    ))) getOrElse None
  }
}



