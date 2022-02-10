package fileReader.model

import scala.util.Try

case class Country(id: Int,
                   code: String,
                   name: String,
                   continent: String,
                   wikipedia_link: String,
                   keywords: Option[String] = None)

object  Country {
  def fromCsvLine(line: Array[String]): Option[Country] = {
      parseCountry(line)
    }

  def parseCountry(line: Array[String]): Option[Country] = {
    (Try(line(0).toInt).toOption,
      Try(line(1)).toOption,
      Try(line(2)).toOption,
      Try(line(3)).toOption,
      Try(line(4)).toOption,
      Try(line(5)).toOption) match {
      case (Some(id), Some(code), Some(name), Some(continent), Some(wikipedia_link), Some(keywords))
        => Some(Country(id, code, name, continent, wikipedia_link, Some(keywords)))
      case (Some(id), Some(code), Some(name), Some(continent), Some(wikipedia_link), None)
      => Some(Country(id, code, name, continent, wikipedia_link))
      case _ => None
    }
  }
}


