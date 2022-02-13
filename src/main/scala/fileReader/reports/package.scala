package fileReader

import fileReader.model.{Airport, Country, Runway}
import fileReader.query.{printCountry, queryAllCountryAirports}


package object reports {

  //10 countries with the most airports
  def top10CountriesAirport(countries: List[Country], airports: List[Airport]): Unit ={
    println("Getting countries... (it may take some time)")
    countries
      .sortWith( (firstCountry,secondCountry) => {airports.count(_.iso_country == firstCountry.code) > airports.count(_.iso_country == secondCountry.code)})
      .take(10)
      .foreach(printCountryWithAirportCount(_,airports))
  }

  //10 countries with the least airports
  def flop10CountriesAirport(countries: List[Country], airports: List[Airport]): Unit ={
    println("Getting countries... (it may take some time)")
    countries
      .sortWith( (firstCountry,secondCountry) => {airports.count(_.iso_country == firstCountry.code) < airports.count(_.iso_country == secondCountry.code)})
      .take(10)
      .foreach(printCountryWithAirportCount(_,airports))
  }

  //display the 10 most common latitudes and the number of their occurrence
  def top10MostCommonRunwayLatitude(runways: List[Runway]): Unit = {
    println("10 most common runway latitude :")
    runways
      .filter(runwayTargeted => runwayTargeted.le_ident.nonEmpty) //remove none values, since le_ident is an Option
      .groupBy(_.le_ident) //Group all runways by their latitude
      .view
      .mapValues(_.length) //find length by runway latitude
      .toSeq
      .sortWith(_._2 > _._2) //order by size
      .toList
      .take(10)
      .foreach(println)
  }

  // print a country and how many airport it has
  def printCountryWithAirportCount(countrySelected: Country, airports: List[Airport]): Unit ={
    printCountry(countrySelected)
    println("Airport Count : " + airports.count(_.iso_country == countrySelected.code))
  }

  //print a latitude and how many runways have this latitude
  def printLatitudeRunwayCount(latitude: Float, runways: List[Runway]): Unit ={
    println("latitude : " + latitude)
    println("Count : " + runways.count(_.he_latitude_deg.get == latitude))
  }

  //print all types of runways that exist in a selected country
  def TypeOfRunwayNational(countrySelected: Country, airports: List[Airport], runways: List[Runway]): Unit ={
    printCountry(countrySelected)
    println("Types of runways (it may take some time to display) :")
    runways.filter(runwayTargeted =>
      queryAllCountryAirports(countrySelected,airports)
          .exists(airportTargeted => airportTargeted.ident == runwayTargeted.airport_ident) ) //check if a runway exist in any airport of the selected country
      .groupBy(_.surface)
      .keys
      .foreach(println)
  }
}
