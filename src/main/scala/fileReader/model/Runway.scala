package fileReader.model

import scala.util.Try

final case class Runway(id: Int,
                        airport_ref: Int,
                        airport_ident: String,
                        length_ft: Option[Int],
                        width_ft: Option[Int],
                        surface: String,
                        lighted: Option[Int],
                        closed: Option[Int],
                        le_ident: String,
                        le_latitude_deg: Option[Float],
                        le_longitude_deg: Option[Float],
                        le_elevation_ft: Option[Int],
                        le_heading_degT: Option[Float],
                        le_displaced_threshold_ft: Option[Int],
                        he_ident: Option[String],
                        he_latitude_deg: Option[Float],
                        he_longitude_deg: Option[Float],
                        he_elevation_ft: Option[Int],
                        he_heading_degT: Option[Float],
                        he_displaced_threshold_ft: Option[Int])

object Runway{

  def fromCsvLine(line: Array[String]): Option[Runway] = {
  parseRunway(line)
}

  def parseRunway(line: Array[String]): Option[Runway] = {
    Try(Some(Runway(
      line(0).toInt,
      line(1).toInt,
      line(2),
      Try(Some(line(3).toInt)) getOrElse None,
      Try(Some(line(4).toInt)) getOrElse None,
      line(5),
      Try(Some(line(6).toInt)) getOrElse None,
      Try(Some(line(7).toInt)) getOrElse None,
      line(8),
      Try(Some(line(9).toFloat)) getOrElse None,
      Try(Some(line(10).toFloat)) getOrElse None,
      Try(Some(line(11).toInt)) getOrElse None,
      Try(Some(line(12).toFloat)) getOrElse None,
      Try(Some(line(13).toInt)) getOrElse None,
      Try(Some(line(14))) getOrElse None,
      Try(Some(line(15).toFloat)) getOrElse None,
      Try(Some(line(16).toFloat)) getOrElse None,
      Try(Some(line(17).toInt)) getOrElse None,
      Try(Some(line(18).toFloat)) getOrElse None,
      Try(Some(line(18).toInt)) getOrElse None
    ))) getOrElse None
  }

}


