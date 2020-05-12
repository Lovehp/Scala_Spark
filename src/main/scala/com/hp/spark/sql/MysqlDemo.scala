package com.hp.spark.sql

import org.apache.spark.sql.{DataFrame, SaveMode}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object MysqlDemo {
  private val url = "jdbc:mysql://10.1.235.117:3307/airmdp"
  private val user = "umobile"
  private val pwd = "umobile"
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("MysqlDemo").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val hiveContext = new HiveContext(sc)

    //1. read
    val mktCamItem = read(hiveContext,"mkt_cam_item")
    mktCamItem.show(10)

    //2. save
    mktCamItem.registerTempTable("mkt_cam_item")
    val sqlStr = "select mkt_cam_item_id,mkt_campaign_id,item_type from mkt_cam_item limit 10"
    val hpTest = hiveContext.sql(sqlStr)
    save(hpTest,hiveContext,"hp_test")

    sc.stop()
  }

  def read(hiveContext: HiveContext,tab:String):DataFrame={
    val msyqlDF = hiveContext.read.format("jdbc").options(
      Map("url" -> this.url,
          "user" -> this.user,
          "password" -> this.pwd,
          "driver" -> "com.mysql.jdbc.Driver",
          "dbtable" -> tab)
    ).load()
    msyqlDF
  }

  def save(df:DataFrame,hiveContext: HiveContext,tab:String):Unit={
    val prop = new java.util.Properties()
    prop.setProperty("user",this.user)
    prop.setProperty("password",this.pwd)
    df.write.mode(SaveMode.Append).jdbc(url,tab,prop)
  }

}
