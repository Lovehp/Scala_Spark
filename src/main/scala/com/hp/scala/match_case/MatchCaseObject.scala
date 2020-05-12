package com.hp.scala.match_case

object MatchCaseObject extends App {

  /**
    * 匹配样例类，样例对象
    * @param ele
    * @return
    */
  def coMatch(ele:Any)=ele match {
    case SubmitTask(id,name) => println(s"submit task-id:${id},task-name:${name}")
    case CheckTimeOutTask => println("checking")
    case HeartBeat(time) => println(s"time is ${time}")
  }


  coMatch(SubmitTask("001","node1"))
  coMatch(CheckTimeOutTask)
  coMatch(HeartBeat(8888888L))
  coMatch(HeartBeat(66666))
}


case object CheckTimeOutTask
case class SubmitTask(id:String,name:String)
case class HeartBeat(time:Long)
