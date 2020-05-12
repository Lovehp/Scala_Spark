package com.hp.spark.mllib

import org.apache.spark.mllib.linalg.{Vectors,Vector}

object testVector {
  def main(args: Array[String]): Unit = {
    val vd:Vector = Vectors.dense(2,0,6) //建立密集向量
    println(vd(2))   //打印密集向量第3个值
    //建立稀疏向量
    val vs:Vector = Vectors.sparse(4,Array(0,1,2,3),Array(9,5,2,7))
    println(vs(2))
  }

}
