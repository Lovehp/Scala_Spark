package com.hp.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

object testZip {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("testZip").setMaster("local[2]")
    val sc  = new SparkContext(sparkConf)
    val arr1 = Array(1,2,3,4,5,6)
    val arr2 = Array("a","b","c","d","e","f")
    val arr3 = Array("g","h","i","j","k","l")
    val arr4 = arr1.zip(arr2).zip(arr3)
    arr4.foreach(print)
    //((1,a),g)((2,b),h)((3,c),i)((4,d),j)((5,e),k)((6,f),l)
    sc.stop()
  }
}
