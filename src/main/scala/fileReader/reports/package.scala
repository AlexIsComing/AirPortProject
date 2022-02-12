package fileReader

import fileReader.model.{Airport, Country, Runway}
import fileReader.query.{printCountry, printRunway, queryAllCountryAirports, querySelectCountry}


package object reports {
  def top10CountriesAirport(countries: List[Country], airports: List[Airport]): Unit ={
    println("Getting countries... (it may take some time)")
    countries
      .sortWith( (firstCountry,secondCountry) => {airports.count(_.iso_country == firstCountry.code) > airports.count(_.iso_country == secondCountry.code)})
      .take(10)
      .foreach(printCountryWithAirportCount(_,airports))
  }

  def flop10CountriesAirport(countries: List[Country], airports: List[Airport]): Unit ={
    println("Getting countries... (it may take some time)")
    countries
      .sortWith( (firstCountry,secondCountry) => {airports.count(_.iso_country == firstCountry.code) < airports.count(_.iso_country == secondCountry.code)})
      .take(10)
      .foreach(printCountryWithAirportCount(_,airports))
  }

  def TypeOfRunwayPerCountry(countrySelected: Country, countries: List[Country], airports: List[Airport], runways: List[Runway]): Unit ={
    TypeOfRunwayNational(countrySelected,airports,runways)
  }

  def top10MostCommonRunwayLatitude(runways: List[Runway]): Unit = {
    runways
      .filter(runwayTargeted => runwayTargeted.le_ident != None)
      .groupBy(_.le_ident)
      .view
      .mapValues(_.length)
      .toMap
      .toSeq
      .sortWith(_._2 > _._2)
      .toList
      .take(10)
      .foreach(println)
  }




  def printCountryWithAirportCount(countrySelected: Country, airports: List[Airport]): Unit ={
    printCountry(countrySelected)
    println("Airport Count : " + airports.count(_.iso_country == countrySelected.code))
  }

  def printLatitudeRunwayCount(latitude: Float, runways: List[Runway]): Unit ={
    println("latitude : " + latitude)
    println("Count : " + runways.count(_.he_latitude_deg.get == latitude))
  }

  def TypeOfRunwayNational(countrySelected: Country, airports: List[Airport], runways: List[Runway]): Unit ={
    printCountry(countrySelected)
    runways.filter(runwayTargeted =>
        airports
          .filter(_.iso_country == countrySelected.code)
          .exists(airportTargeted => airportTargeted.ident == runwayTargeted.airport_ident) )
      .groupBy(_.surface)
      .keys
      .foreach(println)
  }

}
