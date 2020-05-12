package com.hp.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

object TransformRDD {
  private val filePath = "data.txt"

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("TransformRDD")
    sparkConf.setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile(filePath)
    rdd.take(1).foreach(println)

    sc.stop()
  }

}
