package com.hp.spark.sql

import org.apache.spark.sql.{DataFrame, SaveMode}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object PhoenixHBaseDemo {
  private val url = "jdbc:phoenix:c3pretest1:2181"

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("PhoenixHBaseDemo").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val hiveContext = new HiveContext(sc)

    val contactTask = readPhoenixTable(hiveContext,"COO_DEVP.CONTACT_TASk")
    contactTask.show(10)
    contactTask.registerTempTable("CONTACT_TASK")

    val sqlStr = "select contact_task_id,contact_task_title,mkt_campaign_id from CONTACT_TASK limit 10"
    val hpTest = hiveContext.sql(sqlStr)
    hpTest.show()

    savePhoenixTable(hpTest,hiveContext,"COO_TEST.hp_test")
    //spark2.0版本之前不支持JDBC方式保存新表
//    savePhoenixTable(hpTest,hiveContext,"COO_DEVP.hp_test")

    sc.stop()
  }

  // dbTable = database.tableName
  def readPhoenixTable(hiveContext:HiveContext, dbTable:String): DataFrame ={
    val rsDF = hiveContext.read.format("jdbc").options(
      Map("driver" -> "org.apache.phoenix.jdbc.PhoenixDriver",
        "url" -> this.url,
        "dbtable" -> dbTable )).load()
    rsDF
  }

  def savePhoenixTable(df:DataFrame,hiveContext: HiveContext,dbTable:String):Unit = {
    df.write.format("jdbc").options(
      Map("driver" -> "org.apache.phoenix.jdbc.PhoenixDriver",
        "url" -> this.url,
        "dbtable" -> dbTable)
    ).save()
  }

  def savePhoenixTable2(df:DataFrame,hiveContext: HiveContext,dbTable:String):Unit = {
    val prop = new java.util.Properties()
    prop.setProperty("driver","org.apache.phoenix.jdbc.PhoenixDriver")
    prop.setProperty("url",this.url)
    df.write.mode(SaveMode.Append).jdbc(url,dbTable,prop)
  }




}
