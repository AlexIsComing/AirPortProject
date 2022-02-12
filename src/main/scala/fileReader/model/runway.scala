package fileReader.model

import scala.util.Try

final case class Runway(id: Int,
                        airport_ref: Int,
                        airport_ident: String,
                        length_ft: Int,
                        width_ft: Int,
                        surface: String,
                        lighted: Int,
                        closed: Int,
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

  def parseRunway: Array[String]): Option[Runway] = {
  (Try(line(0).toInt).toOption,
  Try(line(1)).toInt,
  Try(line(2)).toOption,
  Try(line(3)).toInt,
  Try(line(4)).toInt,
  Try(line(5)).toOption)
  Try(line(6)).toOption)
  Try(line(7)).toOption)
  Try(line(8)).toOption)
  Try(line(9)).toOption)
  Try(line(10)).toOption)
  Try(line(11)).toOption)
  Try(line(12)).toOption)
  Try(line(13)).toOption)
  Try(line(14)).toOption)
  Try(line(15)).toOption)
  Try(line(16)).toOption)
  Try(line(17)).toOption)
  Try(line(18)).toOption)
  Try(line(19)).toOption)
  Try(line(20)).toOptionmatch) match {

      case (Some(id), Some(code), Some(name), Some(continent), Some(wikipedia_link), Some(keywords))
      => Some(Country(id, code, name, continent, wikipedia_link, Some(keywords)))
      case (Some(id), Some(code), Some(name), Some(continent), Some(wikipedia_link), None)
      => Some(Country(id, code, name, continent, wikipedia_link))
      case _ => None

    }
  }

}


