package com.tyx.scala.test

import org.junit.{Assert, Test}

import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting.quickSort

/**
  * @author liulv 
  */
@Test
class ArrayTest{
  //常用创建数组，直接通过 Array【Int】(1,2,3,4,5),
  //在背后的实现是调用Array的工厂模式Array.apply来构建数组
  val array: Array[Int] = Array[Int](7,1,2,3,4,5,6)
  val array2: Array[Int] = Array.apply(1, 2, 3, 4, 5)
  val array3: Array[String] = Array("Spark", "Scala", "Hello")

  @Test
  def arrFor():Unit = {
    array(0) = 10
    array(array.length - 1) = 1
    for (item <- array) {
      //array(4) = 1
      println(item)
    }
  }

  @Test
  def arrFor2():Unit = {
    for (name <- array3) println(name)
  }

  //数组排序可以使用quickSort对数组进行升序排序
  @Test
  def arrSort():Unit = {
    quickSort(array)
    for (item <- array) {
      println(item)
    }
  }

  //显示数组的内容添加分割符号可以使用mkString
  @Test
  def arrShow():Unit = {
    val rs = array.mkString(",")
    println(rs)
  }

  //如果想在已经有的数组中，通过作用每个元素生成新数组，可以通过yield语法来生成
  // 在不修改array的基础上生成新的数组,这在大数据中意思重大
  @Test
  def newGenerationArr(): Unit = {
    val AddOneArray = for (item <- array) yield item + 1
    println(AddOneArray.mkString(","))

    /**
      * 获取数组中的偶数
      */
    val AddEven = for (item <- array if item % 2 == 0) yield item
    println(AddEven.mkString(","))
  }

  /**
    * Scala中数组本身实现是借助java的数组，所以Array是不可变长度u的
    * 如果要使用可使用ArrayBuffer
    * 数组中可以直接添加数组
    * 进行insert和 remove操作
    */
  @Test
  def arrBuff(): Unit = {
    var arrayBuffers = ArrayBuffer[Int]()
    arrayBuffers += 1
    arrayBuffers += 2
    arrayBuffers += 3
    arrayBuffers += (4, 5, 6, 7, 8, 9)
    arrayBuffers.insert(arrayBuffers.length, 100, 1000)
    //arrayBuffers.remove(arrayBuffers.length - 1)
    println(arrayBuffers.toArray.mkString(","))
  }

  @Test
  def arrIndex(): Unit = {
    for (i <- array.indices) print(array(i) + " ")
  }

  // 集合可以结合函数进行处理，比如map进行加工，filter进行过滤
  @Test
  def arrFlit(): Unit = {
    val arrayFilter = array.filter { _ % 2 == 0 }.map { _ * 10 }
    println(arrayFilter.mkString("  "))
  }

  @Test
  def arrFlit1(): Unit = {
    val arrayFilter = array3.filter { i =>  !i.contains("Spark") && !i.contains("Scala")}
    println(arrayFilter.mkString("  "))
  }

  @Test
  def strTest(): Unit = {
    val arr = "/E:/Workspace/BigData/Hadoop/tbds_demo/tbds-compress-demo/data/123/123/access_log.csv,/E:/Workspace/BigData/Hadoop/tbds_demo/tbds-compress-demo/data/access_log-01.csv,/E:/Workspace/BigData/Hadoop/tbds_demo/tbds-compress-demo/data/access_log.csv.lzo".split(",")

    val fst = Seq("_SUCCESS", "_temporary", ".crc", ".gz", ".bz2", ".lzo",".lz4", ".snappy", "_tmp")
    val result = arr.filter(f => !f.endsWith(fst: *))

    println(result.mkString(","))
  }



}
