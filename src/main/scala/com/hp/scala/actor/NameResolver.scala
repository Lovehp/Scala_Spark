package com.hp.scala.actor

import java.net.{InetAddress, UnknownHostException}

import scala.actors.Actor

/**
  * 由于react不需要返回，作为调用方的actor的调用栈可以被销毁，将线程的资源释放供另一个actor使用。极端情况下，如果程序中所有actor都是用
  * react，则它们可以用单个线程实现。
  */
object NameResolver extends Actor{
  override def act(): Unit = {
    react {
      case (name:String,actor:Actor) =>
        actor ! getIp(name)
        act()
      case "EXIT" => println("Name resolver exiting.")
      case msg =>
        println("Unhandled message:"+msg)
        act()
    }
  }

  def getIp(name: String):Option[InetAddress] ={
    try{
      Some(InetAddress.getByName(name))
    } catch {
      case _:UnknownHostException => None
    }
  }
}
