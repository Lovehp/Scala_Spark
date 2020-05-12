package com.hp.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
  * Spark Streaming整合Flume的第二种方式
  * Approach 2: Pull-based Approach using a Custom Sink
  * 先启动flume 后启动spark Streaming应用程序
  *   flume-ng agent \
  *   --name simple-agent \
  *   --conf $FLUME_HOME/conf \
  *   --conf-file $FLUME_HOME/conf/flume-pull-streaming.conf \
  *   -Dflume.root.logger=INFO,console
  *
  * 传入参数：gulfmoon 41414
  */
object FlumePullWordCount {

  def main(args: Array[String]): Unit = {
    if(args.length != 2) {
      System.err.println("Usage:FlumePullWordCount <hostname> <port>")
      System.exit(1)
    }
    val Array(hostname,port) = args

    val sparkConf = new SparkConf().setAppName("FlumePullWordCount").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf,Seconds(5))

    val flumeStream = FlumeUtils.createPollingStream(ssc,hostname,port.toInt)

    flumeStream.map(x => new String(x.event.getBody.array()).trim).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()

    ssc.start()
    ssc.awaitTermination()
  }

}
