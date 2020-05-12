package com.hp.scala.match_case

object MatchString extends App {

  /*
  匹配字符串
   */
  def contentMatch(str:String) =str match {
    case "dog" => println("小狗")
    case "cat" => println("小猫")
    case "1" => println("数字1")
    case _ => println("匹配失败")
  }

  contentMatch("cat")
  contentMatch("1")
  contentMatch("2")

  /**
    * 匹配类型
    * @param ele
    * @return
    */
  def typeMatch(ele:Any)=ele match {
    case x:Int => println(s"Int:${x}")
    case y:Double => println(s"Double:${y}")
    case z:String => println(s"String:${z}")
    case _ => println("match failure")
  }

  typeMatch("hello")
  typeMatch(2)
  typeMatch(2d)

}
