package com.hp.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingDemo {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("SparkStreamingDemo").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf,Seconds(1))

    val lines = ssc.socketTextStream("10.1.235.114",9999)
    // Count each word in each batch
    // Split each line into words
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word,1))
    val wordCounts = pairs.reduceByKey(_ + _)
    // Print the first ten elements of each RDD generated in this DStream to the console
    wordCounts.print()
    // Start the computation
    ssc.start()
    // Wait for the computation to terminate
    ssc.awaitTermination()
  }

}
