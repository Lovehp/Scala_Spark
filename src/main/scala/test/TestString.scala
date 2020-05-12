package test

object TestString {
  def main(args: Array[String]): Unit = {
    val str = " 3100, 1324, 2, 2, '1000', 95550011, 95550011, '2018-08-28', '2018-08-28', '2018-08-28', 't', "
    println(str)
    val revStr = str.reverse
    println(revStr)
    val begin = revStr.indexOf(",")
    val dealStr = revStr.substring(begin+1).reverse
    println(revStr.substring(begin+1))
    println(dealStr)


  }

}
