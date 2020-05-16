package com.tyx.scala.actor.basics

trait ScalaTrait {
  def toCode()
}

object HelloActor {
  def main(args: Array[String]): Unit = {
    val succ = (x: Int) => x + 1

    val anonfun1 = new Function[Int, Int] {
      def apply(x: Int): Int = x + 1
    }

    assert(succ(0) == anonfun1(0))
  }
}

class Hello(name: String, content: String)

case class HelloBack(name: String, content: String)
