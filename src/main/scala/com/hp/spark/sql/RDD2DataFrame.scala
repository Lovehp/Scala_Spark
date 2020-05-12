package com.hp.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

object RDD2DataFrame {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("HDFSFile").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
////    val rdd1 = sc.textFile("/hupeng/data/user/user.txt")
//    val rdd1 = sc.parallelize(Array("john|18","tom|20","jerry|22"))
//    rdd1.foreach(println)
    val sqlContext = new SQLContext(sc)

    var result = Set[Map[String,String]]()
    result += Map("name" -> "John","age" -> "18")
    result += Map("name" -> "Tom" ,"age" -> "20")
    val resultRDD = set2RDD(sc,result)


    rdd2DFByImplicit(sqlContext,resultRDD)
    sc.stop()

  }

  def rdd2DFByStructType(sqlContext: SQLContext,rdd: RDD[String]):DataFrame={
    //step1:从原来的RDD 创建一个行 RDD
    val peopleRow = rdd.map(_.split("\\|")).map(p => Row(p(0),p(1).toInt))

    //step2:创建由一个 StructType 表示的模式，并且与第一步创建的RDD的行结构相匹配
    import org.apache.spark.sql.types.StringType
    val schema = StructType(Array(StructField("name", StringType, true), StructField("age", IntegerType, true)))

    //step3:在行RDD上通过 createDataFrame方式应用模式
    val peopleDF = sqlContext.createDataFrame(peopleRow,schema)
    peopleDF.printSchema()
    peopleDF.show(5)
    peopleDF.registerTempTable("peopleTable")
    //然后对表操作
    val teenagers = sqlContext.sql("select name,age from peopleTable where age <= 20")
//    teenagers.map(t => "name:" + t(0)).collect().foreach(println)
    peopleDF
  }

  def rdd2DFByImplicit(sqlContext:SQLContext,rdd:RDD[String]):DataFrame={
    //方式二、通过反射
    import sqlContext.implicits._
    val peopleDF = rdd.map(_.split("\\|")).map(p => Person(p(0),p(1).trim.toInt)).toDF()
    //注册成一个表
    peopleDF.registerTempTable("peopleTable1")
    peopleDF.printSchema()
    peopleDF.show()
    peopleDF
  }

  def set2RDD(sc:SparkContext,resultSet:Set[Map[String,String]]):RDD[String]={
    val resultArray = ArrayBuffer[String]()
    for (resultMap <- resultSet) {
      val rowStr = resultMap.values.mkString("|")
      println(rowStr)
      resultArray += rowStr
    }
    val result = resultArray.toArray
    val rdd = sc.parallelize(result)
    rdd
  }

  case class Person(name: String, age: Int)

}
