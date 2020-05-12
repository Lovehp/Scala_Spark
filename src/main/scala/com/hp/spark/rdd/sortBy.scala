package com.hp.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

object sortBy {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("sortBy").setMaster("local[2]")
    val sc  = new SparkContext(sparkConf)
    val str = sc.parallelize(Array((5,"b"),(6,"a"),(1,"f"),(3,"d"),(4,"c"),(2,"e")))
//    val str1 = str.sortBy(word => word._1,true)   //按第一个数据排序
    val str2 = str.sortBy(word => word._2,true)   //按第二个数据排序
//    str1.foreach(print)
    str.foreach(print)
    str2.foreach(print)
    sc.stop()
  }

}
