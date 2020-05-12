package com.hp.spark.streaming

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, ConsumerStrategy, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
  * Spark Streaming对接Kafka的方式二
  * 传入参数: gulfmoon:9092 hello_topic
  * 172.16.21.206:9092,172.16.21.163:9092,172.16.21.228:9092 SparkStreaming_Test
  */
object KafkaDirectWordCount {

  def main(args: Array[String]): Unit = {
    if( args.length != 2) {
      System.err.println("Usage:KafkaDirectWordCount <brokers> <topics>")
      System.exit(1)
    }
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("KafkaDirectWordCount")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    //测试kafka 0-8版本
//    runStreamingBy008(ssc,args)

    //测试kafka 0-10版本
    runStreamingBy010(ssc,args)
    ssc.start()
    ssc.awaitTermination()
  }

  def runStreamingBy010(ssc:StreamingContext,args:Array[String]):Unit={
    val Array(brokers,topics) = args
    val kafkaParams = new mutable.HashMap[String,Object]()
    kafkaParams.put("bootstrap.servers",brokers)
    kafkaParams.put("group.id","hp1")
    kafkaParams.put("key.serializer",classOf[StringSerializer].getName)
    kafkaParams.put("key.deserializer",classOf[StringDeserializer].getName)
    kafkaParams.put("value.serializer",classOf[StringSerializer].getName)
    kafkaParams.put("value.deserializer",classOf[StringDeserializer].getName)
    val offset = new mutable.HashMap[TopicPartition,Long]()
    offset += new TopicPartition(topics,0) -> 20L

    val topicSet:Set[String] = topics.split(",").toSet
    val value:ConsumerStrategy[String,String] = ConsumerStrategies.Subscribe(topicSet,kafkaParams,offset)
    val lines:InputDStream[ConsumerRecord[String,String]] = KafkaUtils.createDirectStream(ssc,LocationStrategies.PreferConsistent,value)
    lines.foreachRDD((r,t) => {
      println(s"count time:${t}")
      r.foreach(s=>{
        println(s.key())
        println(s.value())
        println("123")
      })
    })
  }

  def runStreamingBy008(ssc:StreamingContext,args:Array[String]):Unit={
//    val Array(brokers,topics) = args
//    val topicsSet = topics.split(",").toSet
//    val kafkaParams = Map[String,String]("metadata.broker.list"-> brokers)
//    val messages = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams,topicsSet)
//    messages.map(_._2).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()
  }

}
