package test

import com.hp.util.ConfigUtil

object TestModule {

  def main(args: Array[String]): Unit = {
    println("hello world")
    println(ConfigUtil.getProperty("jdbcUrl"))
  }

}
