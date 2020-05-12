package com.hp.spark.mllib

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

object testSummary {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("testSummary").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("file:///d:/data/a.txt").map(_.split(" ").map(_.toDouble))
                    .map(line => Vectors.dense(line))
    val summary = Statistics.colStats(rdd)
    println(summary.mean)
    println(summary.variance)    //计算标准差
    //计算曼哈顿距离
    println(summary.normL1)
    //计算欧几里得距离
    println(summary.normL2)

    sc.stop()
  }

}
