package com.tyx.scala.basices

/**
 * Scala基本控制结构，顺序、条件、循环，这个三个与java的控制结构一致
 */
object ControlStructures {
  def main(args: Array[String]): Unit = {
    println("Scala")
    val age = 30
    /**
      * if else在Scala中是有值的，但是java中 if else是没有值
      */
    val result = if (age > 25) "Worker" else "Student"
    println(result)

  }
}