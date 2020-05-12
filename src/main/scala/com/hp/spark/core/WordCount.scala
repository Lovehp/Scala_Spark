package com.hp.spark.core

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setAppName("WordCount")
    sparkConf.setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    //通过parallelize方式创建RDD
    val dataArray = Array("a b c d","a c d f","b c d e")
    val dataRDD = sc.parallelize(dataArray)
    //为了展示过程，将代码复杂化
    val rdd1 = dataRDD.flatMap(line =>{
      println("flatMap:"+line)
      line.split(" ")
    })
    val rdd2 = rdd1.map(word =>{
      println("map:"+word)
      (word,1)
    })
    val rdd3 = rdd2.reduceByKey((_ + _))
    rdd3.foreach(println)

    sc.stop()
  }

}
