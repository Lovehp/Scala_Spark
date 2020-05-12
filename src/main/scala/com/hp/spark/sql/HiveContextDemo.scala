package com.hp.spark.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext

object HiveContextDemo {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("HiveContextDemo").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)

    val hiveContext = new HiveContext(sc)
    val databases = hiveContext.sql("show databases")
    databases.show()


    sc.stop()
  }

}
