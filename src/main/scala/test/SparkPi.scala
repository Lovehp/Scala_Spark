package test

import com.hp.util.LogUtil
import org.apache.spark.{SparkConf, SparkContext}

import scala.math.random

object SparkPi {

  private val log = new LogUtil
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("sparkPi")
    val sc = new SparkContext(sparkConf)
    val slices = if (args.length > 0 ) args(0).toInt else 2
    val n = math.min(100000L * slices,Int.MaxValue).toInt
    for (i <- 1 to 300) {
      val count = sc.parallelize(1 until n,slices).map{i =>
        val x = random * 2 - 1
        val y = random * 2 - 1
        if (x*x +y*y <=1 ) 1 else  0
      }.reduce(_ + _)
      log.info(s"pi is roughly ${4.0 * count / (n-1) }")
    }

    sc.stop()
  }

}
