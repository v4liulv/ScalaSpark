package com.tyx.scala.basices

import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting.quickSort

object ArrayOps {
  def main(args: Array[String]): Unit = {

    //    var array = new Array[Int](5)
    /**
     * 1 常用创建数组，直接通过 Array【Int】(1,2,3,4,5),
     *  在背后的实现是调用Array的工厂模式Array.apply来构建数组
     * 2 数组排序可以使用quickSort对数组进行升序排序
     * 3 显示数组的内容添加分割符号可以使用mkString
     * 4 如果想在已经有的数组中，通过作用每个元素生成新数组，可以通过yield语法来生成
     *   在不修改array的基础上生成新的数组,这在大数据中意思重大
     * 5 集合可以结合函数进行处理，比如map进行加工，filter进行过滤
     */
    //    var array = Array[Int](1,2,3,4,5)
    val array = Array.apply(1, 2, 3, 4, 5)
    array(0) = 10
    array(array.length - 1) = 1
    for (item <- array) {
      //array(4) = 1
      println(item)
    }

    val names = Array("Spark", "Scala", "Hello")
    for (name <- names) println(name)

    /**
     * Scala中数组本身实现是借助java的数组，所以Array是不可变长度u的
     * 如果要使用可使用ArrayBuffer
     * 数组中可以直接添加数组
     */
    var arrayBuffers = ArrayBuffer[Int]()
    arrayBuffers += 1
    arrayBuffers += 2
    arrayBuffers += 3
    arrayBuffers += (4, 5, 6, 7, 8, 9)
    arrayBuffers.insert(arrayBuffers.length, 100, 1000)
    arrayBuffers.remove(arrayBuffers.length - 1)
    /**
     * 当需要进行多线程ArrayBuffer转变为Array可调用toArray，元素不可删减，但是其中的元素可修改，但是在多线程操作时候，需要考虑并发写的问题
     */
    arrayBuffers.toArray
    for (arrayBuffer <- arrayBuffers) println("arrayBuffer = " + arrayBuffer)

    for (i <- array.indices) print(array(i) + " ")

    println
    println(array.sum)
    println(array.max)

    quickSort(array)
    for (item <- array) print(item + " ")
    println("mkString_array = " + array.mkString(","))

    val AddOneArray = for (item <- array) yield item + 1
    println(AddOneArray.mkString("  "))

    /**
     * 获取数组中的偶数
     */
    val AddEven = for (item <- array if item % 2 == 0) yield item
    println(AddEven.mkString("  "))

    val arrayFilter = array.filter { _ % 2 == 0 }.map { _ * 10 }
    println(arrayFilter.mkString("  "))

  }

}