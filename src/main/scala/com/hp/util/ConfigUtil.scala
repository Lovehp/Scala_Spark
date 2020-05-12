package com.hp.util

import java.io.FileInputStream
import java.util.Properties

/**
 */
object ConfigUtil {
  private var prop = new Properties()

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