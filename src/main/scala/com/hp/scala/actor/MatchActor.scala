package com.hp.scala.actor
import scala.actors.Actor._

object MatchActor extends App {
  val intActor = actor {
    receive {
      case x:Int => println("Got an Int:"+x)
    }
  }
  intActor ! "hello"
  intActor ! Math.PI
  intActor ! 12
}
