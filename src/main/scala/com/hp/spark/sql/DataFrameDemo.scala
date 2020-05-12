package com.hp.spark.sql

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

object DataFrameDemo {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("DataFrameDemo").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("OFF")
    runMode2(sc)
    sc.stop()
  }

  def runMode1(sc:SparkContext):Unit={
    val sqlContext = new SQLContext(sc)
    val rdd1 = sc.parallelize(Array("1 a 18","2 b 19","3 c 20"))
    val rdd2 = sc.parallelize(Array("2 b cs","4 d nx"))
    val userInfoRow = rdd1.map(_.split(" ")).map(p => Row(p(0),p(1),p(2).toInt))
    val userAreaRow = rdd2.map(_.split(" ")).map(p => Row(p(0),p(1),p(2)))

    //定义schema
    val schema1 = StructType(Array(StructField("id",StringType,true),StructField("name",StringType,true),StructField("age",IntegerType,true)))
    val schema2 = StructType(Array(StructField("id",StringType,true),StructField("name",StringType,true),StructField("area",StringType,true)))
    val userInfoDF = sqlContext.createDataFrame(userInfoRow,schema1)
    val userAreaDF = sqlContext.createDataFrame(userAreaRow,schema2)
    userInfoDF.show()
    userAreaDF.show()


    val select_arr=ArrayBuffer[String](s"a.id",s"""a.name""",s"""a.age""",s"b.area")
    //DataFrame操作
    //1. left join
    val result = userInfoDF.alias("a").join(userAreaDF.alias("b"),userInfoDF("id")===userAreaDF("id"),"left").selectExpr(select_arr:_*).cache()
    result.show()
    //注册成临时表
    result.registerTempTable("result")
    val sql = "select id,name,age,area,(case when area is null then 0 else 1 end) as flag from result"
    sqlContext.sql(sql).show()
  }

  def runMode2(sc:SparkContext):Unit={
    //hiveContext支持case when语法
    val hiveContext = new HiveContext(sc)
    val rdd1 = sc.parallelize(Array("1 a 18","2 b 19","3 c 20"))
    val rdd2 = sc.parallelize(Array("2 b cs","4 d nx"))
    val userInfoRow = rdd1.map(_.split(" ")).map(p => Row(p(0),p(1),p(2).toInt))
    val userAreaRow = rdd2.map(_.split(" ")).map(p => Row(p(0),p(1),p(2)))

    //定义schema
    val schema1 = StructType(Array(StructField("id",StringType,true),StructField("name",StringType,true),StructField("age",IntegerType,true)))
    val schema2 = StructType(Array(StructField("id",StringType,true),StructField("name",StringType,true),StructField("area",StringType,true)))
    val userInfoDF = hiveContext.createDataFrame(userInfoRow,schema1)
    val userAreaDF = hiveContext.createDataFrame(userAreaRow,schema2)
    userInfoDF.show()
    userAreaDF.show()

    val select_arr=ArrayBuffer[String](s"a.id",s"""a.name""",s"""a.age""",s"b.area")
    //DataFrame操作
    //1. left join
    val result = userInfoDF.alias("a").join(userAreaDF.alias("b"),userInfoDF("id")===userAreaDF("id"),"left").selectExpr(select_arr:_*).cache()
    result.show()
    //注册成临时表
    result.registerTempTable("result")
    val sql = "select id,name,age,area,(case when area is null then 0 else 1 end) as flag from result"
    hiveContext.sql(sql).show()
  }

}
