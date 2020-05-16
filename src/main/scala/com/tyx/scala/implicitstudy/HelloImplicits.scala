package com.tyx.scala.implicitstudy

/**
 * 
 * implicit 隐式转换，可以把两个不相关的类，使用隐身转换可以调用第一个类的.第二个类的方法
 * 也可使用import的方式导入一堆的隐身转换
 */
class Man(val name: String)

object Man{
 implicit def man2SuperMan(man: Man): SuperMan = new SuperMan(man.name)
}

class SuperMan(val name: String){
  def makeMiracles() : Unit = println(this.name + " Wow Wow Wod..")
}

object implicits{
  implicit def man2SuperMan(man: Man): SuperMan = new SuperMan(man.name)
}

object HelloImplicits {

  def main(args: Array[String]): Unit = {
    val man = new Man("Scala")
    man.makeMiracles()

    implicit val content: String = "Money"
    talk("Scala")("Spark")
  }

  def talk(name: String)(implicit content: String): Unit = println(name + ": " + content)
}