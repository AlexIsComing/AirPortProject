package fileReader

import fileReader.model.{Airport, Country, Runway}
import fileReader.query.{displayAirportAndRunways, printCountry, queryAllCountryAirports, querySelectCountry}
import fileReader.service.{CSV, ReadResult}
import fileReader.reports.{TypeOfRunwayNational, flop10CountriesAirport, top10CountriesAirport, top10MostCommonRunwayLatitude}

import scala.annotation.tailrec
import scala.util.{Failure, Success}
import scala.util.Try

object Main {

  @tailrec
  def subMenuReports(countries: List[Country], runways: List[Runway], airports: List[Airport]): Unit ={
    println("Please Select an option")
    println("1) 10 countries with highest number of airports")
    println("2) 10 countries with lowest number of airports")
    println("3) Type of runways per country")
    println("4) Top 10 most common runway latitude")
    println("5) Return to main menu")
    Try(scala.io.StdIn.readInt() ) match {

      case Success(i) => i match { //check if any error
        case 1 =>
          top10CountriesAirport(countries,airports)
        case 2 =>
          flop10CountriesAirport(countries, airports)
        case 3 =>
          println("Please enter a country name or code")
          Try(querySelectCountry(scala.io.StdIn.readLine(),countries) ) match { //get country
            case Success(i) => //check if any error
              if(i.isEmpty) { //check if there is no country returned
                println("No country found, please try again")
                subMenuReports(countries, runways, airports)
              }
              else {
                TypeOfRunwayNational(i.get, airports, runways)
                mainMenu(countries,runways,airports)
              }
            case Failure(e) =>
              println(e.getMessage)
              subMenuReports(countries, runways, airports)
          }

        case 4 =>
          top10MostCommonRunwayLatitude(runways)
        case 5 => println("Return to main menu")
        case _ =>
          subMenuReports(countries, runways, airports)
      }
      case Failure(e) =>
        println(e.getMessage)
        subMenuReports(countries, runways, airports)
    }
  }

  @tailrec
  def subMenuQuery(countries: List[Country], runways: List[Runway], airports: List[Airport]): Unit ={
    println("Please enter a country name or code")

    Try(querySelectCountry(scala.io.StdIn.readLine(),countries) ) match {
      case Success(i) => //check if any error
        if(i.isEmpty) { //check if there is no country returned
          println("No country found, please try again")
          subMenuQuery(countries, runways, airports)
        }
        else {
          printCountry(i.get)
          queryAllCountryAirports(i.get, airports).foreach(displayAirportAndRunways(_,runways))
        }
      case Failure(e) =>
        println(e.getMessage)
        subMenuQuery(countries, runways, airports)
    }
  }

  @tailrec
  def mainMenu(countries: List[Country], runways: List[Runway], airports: List[Airport]): Unit ={
    println("Welcome to our airport application\nPlease Select an option")
    println("1) Query")
    println("2) Reports")
    println("3) Exit")
    Try(scala.io.StdIn.readInt() ) match {

      case Success(i) => i match { //check if any error
        case 1 =>
          println("fileReader/query")
          subMenuQuery(countries, runways, airports)
          mainMenu(countries, runways, airports)
        case 2 =>
          println("Reports")
          subMenuReports(countries, runways, airports)
          mainMenu(countries, runways, airports)
        case 3 =>
          println("Exit")
        case _ =>
          mainMenu(countries, runways, airports)
      }
      case Failure(e) =>
        println(e.getMessage)
        mainMenu(countries, runways, airports)
    }
  }

  def main(args: Array[String]): Unit = {

    //reading the csv
    val countries = CSV.read("countries.csv", Country.fromCsvLine)
    val airports = CSV.read("airports.csv", Airport.fromCsvLine)
    val runways = CSV.read("runways.csv", Runway.fromCsvLine)

    mainMenu(countries.lines.toList, runways.lines.toList, airports.lines.toList)
  }
}
