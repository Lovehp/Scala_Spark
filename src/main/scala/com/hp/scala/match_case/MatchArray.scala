package com.hp.scala.match_case

object MatchArray extends App{

  /**
    * 匹配数组
    * @param arr
    * @return
    */
  def arrayMatch(arr:Any)=arr match {
    case Array(0) => println("只有一个0元素的数组")
    case Array(0,_) => println("以0开头，拥有两个元素的数组")
    case Array(1,_,3) => println("以1开头，3结尾的任意三个元素的数组")
    case Array(_*) => println("N个元素的数组")
  }

  arrayMatch(Array(0))
  arrayMatch(Array(0,2))
  arrayMatch(Array(1,true,3))
  arrayMatch(Array(1,3,5,7,9))

}
