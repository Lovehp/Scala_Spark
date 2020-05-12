package com.hp.scala.match_case

object MatchTuple extends App{

  def tupMatch(tup:Any)=tup match {
    case (3,x,y) => println("第一个元素为3的元组")
    case (_,2) => println("第二个元素为2，拥有两个元素的元组")
    case (x,y,z) => println("拥有三个元素的任意元组")
    case _ => println("匹配失败")
  }

  tupMatch((3,2,1))
  tupMatch((3,2))
  tupMatch((4,2,1))
  tupMatch((1,2,3,4,5))

}
