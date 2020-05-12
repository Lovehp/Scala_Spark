package com.hp.spark.core

import org.apache.spark.{SparkConf, SparkContext}

object HDFSFile {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("HDFSFile").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val rdd1 = sc.textFile("/hupeng/data/user/user.txt")

    rdd1.foreach(println)
    sc.stop()
  }

}
