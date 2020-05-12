package com.hp.spark.mllib

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 与计算两组数据相关系数不同，单个数据集相关系数的计算首先要将数据转化成本地向量，之后再进行计算
  */
object testSingleCorrect {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("testSingleCorrect").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("file:///d:/data/x.txt").map(_.split(" ").map(_.toDouble)).map(line => Vectors.dense(line))
    println(Statistics.corr(rdd,"spearman"))  //使用斯皮尔曼计算相关系数

    sc.stop()
  }

}
