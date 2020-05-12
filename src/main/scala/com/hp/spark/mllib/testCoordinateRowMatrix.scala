package com.hp.spark.mllib

import org.apache.spark.mllib.linalg.distributed.{CoordinateMatrix, MatrixEntry}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 坐标矩阵
  */
object testCoordinateRowMatrix {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("testCoordinateRowMatrix").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("file:///d:/data/a.txt").map(_.split(" ").map(_.toDouble))
                  .map(vue => (vue(0).toLong,vue(1).toLong,vue(2)))
                    .map(vue2 => new MatrixEntry(vue2 _1,vue2 _2,vue2 _3))
    val crm = new CoordinateMatrix(rdd)
    println(crm.entries.foreach(println))
    sc.stop()
  }

}
