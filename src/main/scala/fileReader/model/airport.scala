package fileReader.model

import scala.util.Try

case class Airport(id: Int,
                   ident: String,
                   Type: String,
                   name: String,
                   latitude_deg: double,
                   longitude_deg: double,
                   elevation_ft: double,
                   continent: String,
                   iso_country: String,
                   iso_region: String,
                   municipality: String,
                   scheduled_service: option[String] = None,
                   gps_code: option[String] = None,
                   iata_code: option[String] = None,
                   local_code: option[String] = None,
                   home_link: option[String] = None,
                   wikipedia_link: option[String] = None,
                   keywords: option[String] = None)

object Airport {
  def fromCsvLine(line: Array[String]): Option[Airport] = {
    parseAirport(line)
  }

  def parseAirport(line: Array[String]): Option[Airport] = {
    (Try(line(0).toInt).toOption,
      Try(line(1)).toOption,
      Try(line(2)).toOption,
      Try(line(3)).toOption,
      Try(line(4)).toOption,
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
      Try(line(17)).toOption) match {


      case (Some(id), Some(Type), Some(name), Some(latitude_deg), Some(longitude_deg), Some(elevation_ft),
      Some(continent), Some(iso_country), Some(iso_region), Some(municipality), Some(scheduled_service), Some(gsp_code),
      Some(iata_code), Some(local_link), Some(wikipedia_link), Some(keywords)
      => Some(Airport(id, ident, Type, name,
         latitude_deg, longitude_deg, elevation_ft, continent, iso_country, iso_region, municipality,
         scheduled_service, gps_code, local_code, home_link, wikipedia_link, keywords))

      case _ => None
    }
  }
}



