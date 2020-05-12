package com.hp.spark.streaming

import org.apache.spark.SparkConf
//import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Spark Streaming 对接Kafka 的方式一
  * 传入参数：192.168.122.210:2181 test hello_topic 1
  */
object KafkaReceiverWordCount {

  def main(args: Array[String]): Unit = {
    if (args.length != 4){
      System.err.println("Usage:KafkaReceiverWordCount <zkQuroum> <group> <topics> <numThreads>")
      System.exit(1)
    }

//    val Array(zkQuroum,group,topics,numThreads) = args
//
//    val sparkConf = new SparkConf().setAppName("KafkaRecevierWordCount").setMaster("local[2]")
//    val ssc = new StreamingContext(sparkConf,Seconds(5))
//
//    val topicMap = topics.split(",").map((_,numThreads.toInt)).toMap
//    val messages = KafkaUtils.createStream(ssc,zkQuroum,group,topicMap)
//    messages.map(_._2).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()
//
//    ssc.start()
//    ssc.awaitTermination()
  }

}
