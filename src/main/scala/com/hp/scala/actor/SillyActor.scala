package com.hp.scala.actor

import scala.actors.Actor

object SillyActor extends Actor with App {
  SillyActor.start()
  override def act(): Unit = {
    for (i <- 1 to 5) {
      println("I'm acting")
      Thread.sleep(1000)
    }
  }
}
