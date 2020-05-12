package com.hp.util

import org.slf4j.Logger

/**
  * created by hupeng 2018-12-08
  */
class LogUtil {

  private var logger:Logger = null
  //false：使用控制台输出方式；true：使用slf4j工具
  private var loggerType = false

  def setLoggerType(flag:Boolean):Unit={
    this.loggerType = flag
  }


  def setLogger(logger:Logger):Unit={
    if (this.loggerType){
      this.logger = logger
    }
  }

  def info(msg:Any):Unit={
    if(loggerType)
    {
      logger.info(msg.toString)
    } else {
      println("[Info]["+DateUtil.getNowStringDate+"]:"+msg.toString)
    }
  }

  def debug(msg:Any):Unit={
    if(loggerType)
    {
      logger.debug(msg.toString)
    } else {
      println("[Debug]["+DateUtil.getNowStringDate+"]:"+msg.toString)
    }
  }

  def error(msg:Any):Unit={
    if(loggerType)
    {
      logger.error(msg.toString)
    } else {
      println("[Error]["+DateUtil.getNowStringDate+"]:"+msg.toString)
    }
  }

  def warn(msg:Any):Unit={
    if(loggerType)
    {
      logger.warn(msg.toString)
    } else {
      println("[Warn]["+DateUtil.getNowStringDate+"]:"+msg.toString)
    }
  }




}
