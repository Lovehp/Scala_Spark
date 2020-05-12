package com.hp.spark.mllib

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.{SparkConf, SparkContext}

object testRowMatrix {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("testRowMatrix").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("file:///d:/data/a.txt").map(_.split(' ').map(_.toDouble)).map(line => Vectors.dense(line))
    val rm = new RowMatrix(rdd)
    println(rm.numRows())
    println(rm.numCols())
    sc.stop()
  }

}
