package fileReader

import fileReader.service.CSV
import fileReader.model.Country

object Main {

  def main(args: Array[String]): Unit = {
    val result = CSV.read("countries.csv", Country.fromCsvLine)
    println(result.nbInvalidLine)
    result.lines.foreach(println)
  }
}
