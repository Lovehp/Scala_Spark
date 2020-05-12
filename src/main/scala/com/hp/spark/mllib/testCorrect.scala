package com.hp.spark.mllib

import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

object testCorrect {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("testCorrect").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val rddX = sc.textFile("file:///d:/data/x.txt").flatMap(_.split(" ").map(_.toDouble))
    val rddY = sc.textFile("file:///d:/data/y.txt").flatMap(_.split(" ").map(_.toDouble))
    //计算皮尔森系数
    val correlation:Double = Statistics.corr(rddX,rddY)
    println(correlation)
    //计算斯皮尔曼系数
    val correlation1 = Statistics.corr(rddX,rddY,"spearman")
    println(correlation1)

    sc.stop()
  }

}
