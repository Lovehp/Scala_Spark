package com.hp.scala.actor
import scala.actors.Actor._

object SeriousActor extends App {
  val seriousActor = actor {
    for (i <- 1 to 5)
      println("That is the question.")
    Thread.sleep(1000)
  }
}
