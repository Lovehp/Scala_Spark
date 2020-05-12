//package com.hp.spark.streaming
//
//import org.apache.spark.SparkConf
//import org.apache.spark.streaming.kafka.KafkaUtils
//import org.apache.spark.streaming.{Seconds, StreamingContext}
//
///**
//  * 传入参数：192.168.122.210:2181 test hello_topic 1
//  * 172.16.21.206:9092,172.16.21.163:9092,172.16.21.228:9092 test SparkStreaming_Test 1
//  */
//object KafkaStreamingApp {
//
//  def main(args: Array[String]): Unit = {
//    if(args.length != 4){
//      System.err.println("Usage:KafkaStreamingApp <zkQuorum> <group> <topics> <numThreads>")
//      System.exit(1)
//    }
//
//    val Array(zkQuroum,group,topics,numThreads) = args
//
//    val sparkConf = new SparkConf().setAppName("KafkaStreamingApp").setMaster("local[2]")
//    val ssc = new StreamingContext(sparkConf,Seconds(5))
//
//    val topicMap = topics.split(",").map((_,numThreads.toInt)).toMap
//
//    val messages = KafkaUtils.createStream(ssc,zkQuroum,group,topicMap)
//
//    messages.map(_._2).count().print()
//
//    ssc.start()
//    ssc.awaitTermination()
//  }
//
//}
