package com.hp.spark.mllib

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.{IndexedRow, IndexedRowMatrix}
import org.apache.spark.{SparkConf, SparkContext}


object testIndexedRowMatrix {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("testIndexedRowMatrix").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("file:///d:/data/a.txt").map(_.split(" ").map(_.toDouble))
                      .map(line => Vectors.dense(line))
                      .map((vd) => new IndexedRow(vd.size,vd))
    val irm = new IndexedRowMatrix(rdd)
    println(irm.getClass)
    println(irm.rows.foreach(println))

    sc.stop()
  }

}
