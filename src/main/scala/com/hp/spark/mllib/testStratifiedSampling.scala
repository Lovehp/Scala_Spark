package com.hp.spark.mllib

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 分层抽样
  */
object testStratifiedSampling {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("testStratifiedSampling").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val data = sc.textFile("file:///d:/data/a.txt")
                .map(row => {
                  if (row.length == 3)
                    (row,1)
                  else (row,2)
                })
    val fractions:Map[String,Double] = Map("aa" -> 2)  //设定抽样格式
    //计算抽样样本
    val approxSample = data.sampleByKey(withReplacement = false,fractions,0)
    approxSample.foreach(println)
    sc.stop()
  }

}
