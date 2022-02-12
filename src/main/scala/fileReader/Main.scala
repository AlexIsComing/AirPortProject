package fileReader

import fileReader.model.Country
import fileReader.service.{CSV, ReadResult}

import scala.util.{Failure, Success}
import scala.util.Try

object Main {

  def subMenuReports(countries: List[Country]): Unit ={
    println("Please Select an option")
    println("1) 10 countries with highest number of airports")
    println("2) 10 countries with lowest number of airports")
    println("3) Type of runways per country")
    println("4) Top 10 most common runway latitude")
    println("5) Return to main menu")
    Try(scala.io.StdIn.readInt() ) match {

      case Success(i) => i match {
        case 1 =>
          //code ici
        case 2 =>
          //code ici
        case 3 =>
          //code ici
        case 4 =>
          //code ici
        case 5 => println("Return to main menu")
        case _ =>
          subMenuReports(countries)
      }
      case Failure(e) =>
        println(e.getMessage)
        subMenuReports(countries)
    }
  }


  def subMenuQuery(countries: List[Country]): Unit ={
    println("Please enter a country name or code")

    Try(scala.io.StdIn.readLine() ) match {
      case Success(i) =>
        println("Country input correct : " + i)
        Try(countries.find(line => line.name == "\""+i+"\"" || line.code == "\""+i+"\"").get) match {
          case Success(j) => println(j)
          case Failure(f) =>
            println(f.getMessage)
            subMenuQuery(countries)
        }
      case Failure(e) =>
        println(e.getMessage)
        subMenuQuery(countries)

    }
  }

  def mainMenu(countries: List[Country]): Unit ={
    println("Welcome to our airport application\nPlease Select an option")
    println("1) Query")
    println("2) Reports")
    println("3) Exit")
    Try(scala.io.StdIn.readInt() ) match {

      case Success(i) => i match {
        case 1 =>
          println("query")
          subMenuQuery(countries)
          mainMenu(countries)
        case 2 =>
          println("Reports")
          subMenuReports(countries)
          mainMenu(countries)
        case 3 =>
          println("Exit")
        case _ =>
          mainMenu(countries)
      }
      case Failure(e) =>
        println(e.getMessage)
        mainMenu(countries)
    }
  }

  def main(args: Array[String]): Unit = {
    val countries = CSV.read("countries.csv", Country.fromCsvLine).lines.toList
    val airports = CSV.read("airports.csv", Country.fromCsvLine).lines.toList //à changer
    val runways = CSV.read("runways.csv", Country.fromCsvLine).lines.toList //à changer

    mainMenu(countries)
  }
}
