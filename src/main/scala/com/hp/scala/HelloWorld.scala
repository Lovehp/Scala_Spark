package com.hp.scala

class HelloWorld {

}

object HelloWorld extends App {
    println("hello world")
    val a = Array(2,3,5,7,11)
    println(s"Array a is :${a.mkString(",")}")
    val b = a.map(_ *2)
    println(s"Array a is :${b.mkString(",")}")
}
