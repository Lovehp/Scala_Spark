package com.hp.scala

object MapAndTuple {
  def main(args: Array[String]): Unit = {
    val scores = Map(("Alice",10),("Bob",3),("Cindy",8))
    println(scores)
    val bobScore = scores.get("Bob").get //scores("Bob")
    println(bobScore)
    val johnScore = scores.getOrElse("John",100)
    println(johnScore)
  }

}
