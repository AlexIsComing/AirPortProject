package fileReader

import scala.util.{Failure, Success}
import scala.util.Try

object Main {

  def subMenuReports(): Unit ={
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
          subMenuReports()
      }
      case Failure(e) =>
        println(e.getMessage())
        subMenuReports()
    }
  }


  def subMenuQuery(): Unit ={
    println("Please enter a country name or code")

    Try(scala.io.StdIn.readLine() ) match {
      case Success(i) => i match {
        //code ici
      }
      case Failure(e) =>
        println(e.getMessage())
        subMenuQuery()
    }
  }

  def mainMenu(): Unit ={
    println("Welcome to our airport application\nPlease Select an option")
    println("1) Query")
    println("2) Reports")
    println("3) Exit")
    Try(scala.io.StdIn.readInt() ) match {

      case Success(i) => i match {
        case 1 =>
          println("query")
          subMenuQuery()
          mainMenu()
        case 2 =>
          println("Reports")
          subMenuReports()
        case 3 => println("Exit")
        case _ =>
          mainMenu()
      }
      case Failure(e) =>
        println(e.getMessage())
        mainMenu()
    }
  }

  def main(args: Array[String]): Unit = {
    mainMenu()
  }
}
