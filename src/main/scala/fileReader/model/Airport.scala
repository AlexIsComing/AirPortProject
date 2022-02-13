package fileReader.model

import scala.util.Try

case class Airport(id: Int,
                   ident: String,
                   _type: String,
                   name: String,
                   latitude_deg: Option[Double],
                   longitude_deg: Option[Double],
                   elevation_ft: Option[Double],
                   continent: Option[String],
                   iso_country: String,
                   iso_region: Option[String],
                   municipality: Option[String],
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
      Try(Some(line(4).toDouble)) getOrElse None,
      Try(Some(line(5).toDouble)) getOrElse None,
      Try(Some(line(6).toDouble)) getOrElse None,
      Try(Some(line(7))) getOrElse None,
      line(8),
      Try(Some(line(9))) getOrElse None,
      Try(Some(line(10))) getOrElse None,
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



