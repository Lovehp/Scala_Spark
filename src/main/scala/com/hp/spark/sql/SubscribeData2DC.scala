package com.hp.spark.sql

import com.alibaba.fastjson.JSONObject
import com.hp.util.LogUtil
import org.apache.spark.{SparkConf, SparkContext}

object SubscribeData2DC {
  private val log = new LogUtil
  private val EVENT_TYPE = "businessId:feedbackEvent"

  def main(args :Array[String])= {
    println("Hello, world")
    val sparkConf = new SparkConf().setAppName("orderSync2RTI").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
    val sql_str=args(0)
    log.info("--------------sql_str=" + sql_str)
    val df2 = hiveContext.sql(sql_str)
    val rdd2 = df2.rdd
    log.info("--------------Begin to sync")
    rdd2.foreachPartition {
      x => {
        x.foreach {
          y => {
            val name_event_type = EVENT_TYPE.split(":").apply(0)
            val value_event_type = EVENT_TYPE.split(":").apply(1)
            val p = new JSONObject
            p.put(name_event_type,value_event_type)
            p.put("activeId",y(0))
            p.put("strategyId",y(1))
            p.put("contactPhone",y(2))
            p.put("channelId",y(3))
            p.put("feedbackResult",y(4))
            p.put("staffId",y(5))
            p.put("subTime",y(6))
            p.put("productId",y(7))
            p.put("oprPosCode",y(8))
            println(p.toString)
            // RocketMQProducerManager.sendMessage("TopicTest1", "TagA", "y(0)="+y(0)+",y(1)="+y(1))
          }
        }
      }
    }
    //RocketMQProducerManager.destroy()

//    {"productId":"252792660652","businessId":"feedbackEvent","activeId":"106619","strategyId":"9001","feedbackResult":30,"contactPhone":"8001","subTime":"2017-06-28 00:58:07","channelId":"1002","staffId":"14000480360","oprPosCode":"OPR_T"}
//    {"productId":"270387930319","businessId":"feedbackEvent","activeId":"2118","strategyId":"102877","feedbackResult":30,"contactPhone":"272290481692","subTime":"2017-08-29 10:05:00","channelId":"12345","staffId":"355500444"}

  }

}
