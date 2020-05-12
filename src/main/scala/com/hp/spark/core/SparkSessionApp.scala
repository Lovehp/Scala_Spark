package com.hp.spark.core

import org.apache.spark.sql.SparkSession

/**
 * @Description:
 * @Author: hupeng
 * @Date: 13:45 2019/11/15
 */
object SparkSessionApp extends App {
  val spark = SparkSession.builder().appName("simple application").getOrCreate()

}
