package fileReader

import fileReader.model.{Airport, Country, Runway}

import scala.util.Try

package object query {

  //returns the country that have a code or name that match the input
  def querySelectCountry(input: String, countries: List[Country]): Option[Country] ={
    Try(countries.find(line => line.name == "\""+input+"\"" || line.code == "\""+input+"\"")) getOrElse None
    }

  // returns all airport that belong to a country
  def queryAllCountryAirports(countrySelected: Country, airports: List[Airport]): List[Airport] ={
    airports.filter(_.iso_country == countrySelected.code)
  }

  //display an airport and all of its runways
  def displayAirportAndRunways(airportSelected: Airport, runways: List[Runway]): Unit ={
    printAirport(airportSelected)
    runways.filter(_.airport_ident == airportSelected.ident).foreach(printRunway)
  }

  def printCountry(countrySelected: Country): Unit ={
    println("Country Name : " + countrySelected.name)
  }

  def printAirport(airportSelected: Airport): Unit ={
    println("Airport Name : " + airportSelected.name)
  }

  def printRunway(runwaySelected: Runway): Unit ={
    println("Runway id : " + runwaySelected.id)
  }

}
