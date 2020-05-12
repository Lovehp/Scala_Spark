package com.hp.util

import java.io.FileInputStream
import java.util.Properties

/**
  * created by hupeng 2018-12-07
  * com.hp.spark-submit提交作业时，可以指定外部的配置文件，如果本机调试还是使用ConfigUtil
  */
object PropertiesUtil {
  private val prop = new Properties()

  def init(): Unit = {
    try {
      val input = new FileInputStream("conf.properties")
      prop.load(input)
    } catch  {
      case e:Exception => e.printStackTrace()
        println("Please check the path")
    }
  }

  def getProperty(str:String):String = {
    if(prop.getProperty(str) == null){
      init()
    }
    prop.getProperty(str)
  }
}
