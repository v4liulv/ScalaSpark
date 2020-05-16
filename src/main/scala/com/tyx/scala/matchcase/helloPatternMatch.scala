package com.tyx.scala.matchcase

/**
 * case class 其实就是一个java bean
  *
  * 数据框架
 */
class DataFrameWork

/**
  * 数学框架
  *
  */
case class ComputationFrameWork(name: String, popular: Boolean) extends DataFrameWork {
  def getV = println("......")
}

/**
  * 存储框架
  *
  */
case class StorageFrameWork(name: String, popular: Boolean) extends DataFrameWork

/**
 * 模式匹配学习
 */
object helloPatternMatch {
  def main(args: Array[String]): Unit = {
    //getSalary("Scala")
    getSalary("sada", 6)

    getMatchType(100)

    getMatchCollection(Array("Spark", "aaa"))

    getBigDataType(ComputationFrameWork("Spark", popular = true))

    getValue("Spark", Map("Spark" -> "The hottest!", "Hadoop" -> "The oddest"))

  }

  //对直进行匹配
  def getSalary(name: String, age: Int) {
    name match {
      case "Spark"                         => println("$15万/year")
      case "Hadoop"                        => println("$10万/year")
      case _ if name == "Scala"            => println("$14万/year")
      case _ if name == "Hadoop Mapredcue" => println("$9万/year")
      case _name if age >= 5               => println("name: " + name + " age: " + age + " $9万/year")
      case _                               => println("$10万/year")
    }
  }

  //对类型进行匹配
  def getMatchType(msg: Any) {
    msg match {
      case i: Int            => println("Integer")
      case s: String         => println("String")
      case d: Double         => println("Double")
      case array: Array[Int] => println("Array")
      case _                 => println("Unkown type")

    }
  }

  //对集合进行匹配
  def getMatchCollection(msg: Array[String]) {
    msg match {
      case Array("Scala")         => println("One elements")
      case Array("Scala", "Java") => println("Two elements")
      case Array("Spark", _*)     => println("Many elements begin with Spark")
      case _                      => println("Unkown type")

    }
  }

  //case class 
  def getBigDataType(data: DataFrameWork) {
    data match {
      case ComputationFrameWork(name, popular) => println("name: " + name + " popular :" + popular)
      case StorageFrameWork(name, popular)     => println("name: " + name + " popular :" + popular)
      case _                                   => println("Some auth Type")
    }
  }

  def getValue(key: String, content: Map[String, String]) {
    content.get(key) match {
      case Some(value) => println(value)
      case None        => println("Not find")
    }
  }

}