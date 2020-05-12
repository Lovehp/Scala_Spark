package com.hp.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

object flatMap {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("flatMap").setMaster("local[2]")
    val sc  = new SparkContext(sparkConf)
    val arr = sc.parallelize(Array(1,2,3,4,5))
    val result = arr.map(x => List(x+1)).collect()
    result.foreach(println)

    sc.stop()
  }

}
