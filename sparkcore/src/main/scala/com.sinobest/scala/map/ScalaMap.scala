package com.sinobest.scala.map

import java.util

/**
  * @author liulv 
  * @date 2019/8/22
  * @time 14:51
  * @description
  */
object ScalaMap {

  def main(args: Array[String]): Unit = {
    var map = Map[String,String]("name" -> "jason",
      "age" -> "500",
      "test_100" -> "test_100",
      "test_101" -> "test_101") //引用可变,支持读写操作;

    val mapJava: util.Map[String, String] = new util.HashMap[String, String]()
    mapJava.put("123", "1")
    mapJava.put("1234", "2")

    val keySet = mapJava.keySet()
    val key_iter = keySet.iterator //遍历,迭代map;
    while (key_iter.hasNext) {
      val key = key_iter.next
      println(key + ":" + map.get(key))
    }
  }
}

