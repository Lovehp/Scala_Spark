package com.hp.util

import java.text.SimpleDateFormat
import java.util.{Calendar, Date, TimeZone}

/**
 * Created by LinsongMa on 2015/6/8.
 */
object DateUtil {

  val DEFAULT_FORMAT="yyyy-MM-dd HH:mm:ss"
  val YYYY="yyyy"
  val MM="MM"
  val YYYY_MM="yyyy-MM"
  val DD="dd"
  val YYYY_MM_DD="yyyy-MM-dd"
  val HH="HH"
  val MI="mm"
  val SS="ss"
  val YYYYMMDDHHMI="yyyyMMddHHmm"
  val YYYYMMDDHHMI_TIMESTAMP="yyyy-MM-dd HH:mm:ss"
  val YYYYMMDDHHMISS="yyyyMMddHHmmss"
  val YYYYMMDD="yyyyMMdd"
  val YYYYMMDDHH="yyyyMMddHH"
  val HHMISS="HHmmss"
  val YYYYMM="yyyyMM"


  /**
   * 获取当前时间
   */
  def getNowDate={
    Calendar.getInstance().getTime()
  }

  /**
   * 以默认格式获取当前时间字符串表示
   */
  def getNowStringDate={
    format(getNowDate,DEFAULT_FORMAT)
  }

  /**
   * 以YYYYMMDD返回当前日期
   */
  def getCurrentDateYYYYMMDD={
    format(getNowDate,YYYYMMDD)
  }

  /**
   * 以YYYYMMDDHHMISS返回当然时间
   */
  def getNowTimeYYYYMMDDHHMISS={
    format(getNowDate,YYYYMMDDHHMISS)
  }

  /**
   * 格式化时间
   */
  def format(date:Date,pattern:String)={
    val dateFormat=new SimpleDateFormat(pattern)
    dateFormat.format(date)
  }

  /**
   * 格式化时区时间
   */
  def formattz(date:Date,pattern:String,tz:String)={
    val dateFormat=new SimpleDateFormat(pattern)
    dateFormat.setTimeZone(TimeZone.getTimeZone(tz));
    dateFormat.format(date)
  }

  /**
   * 时间转换
   */
  def parse(date:String,pattern:String)={
    val dateFormat=new SimpleDateFormat(pattern)
    dateFormat.parse(date)
  }

  /**
   * 偏移时间-秒,count为正向前，count为负向后
   */
  def offsetSeconds(date:Date,count:Long)={
    val time=date.getTime()
    new Date(time+count*1000)
  }

  /**
   * 偏移时间-分
   */
  def offsetMinutes(date:Date,count:Long)={
    offsetSeconds(date,count*60)
  }

  /**
   * 偏移时间-小时
   */
  def offsetHours(date:Date,count:Long)={
    offsetMinutes(date,count*60)
  }

  /**
   * 偏移时间-天
   */
  def offsetDays(date:Date,count:Long)={
    offsetHours(date,count*24)
  }

  /**
   * 偏移时间-月
   */
  def offsetMonths(date:Date,count:Int)={
    val calendar=Calendar.getInstance()
    calendar.setTime(date)
    calendar.add(Calendar.MONTH,count)
    calendar.getTime
  }

  /**
   * 返回当月最后一天
   */
  def getLastDay(date:Date=null) ={
    val calendar=Calendar.getInstance()
    if(date!=null)
    {
      calendar.setTime(date)
    }
    calendar.add(Calendar.MONTH,1);
    calendar.set(Calendar.DATE,1);
    calendar.add(Calendar.DATE,-1);
    calendar.getTime
  }

  /**
   * 转换日期输出格式
   *
   * @param YYYYMMDD
   * @return YYYY-MM-DD
   */

  def formatDay(day:String):String ={
    day.substring(0,4)+"-"+day.substring(4,6)+"-"+day.substring(6,8)
  }

  /**
   * 转换月输出格式
   *
   * @param YYYYMM
   * @return YYYY-MM
   */

  def formatMonth(month:String):String ={
    month.substring(0,4)+"-"+month.substring(4,6)
  }

}
