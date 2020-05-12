package com.hp.spark.mllib

import org.apache.spark.mllib.linalg.Matrices

/**
  * 本地矩阵
  */
object testMatrix {
  def main(args: Array[String]): Unit = {
    val mx = Matrices.dense(2,3,Array(1,2,3,4,5,6))  //创建一个本地矩阵
    println(mx)
  }

}
