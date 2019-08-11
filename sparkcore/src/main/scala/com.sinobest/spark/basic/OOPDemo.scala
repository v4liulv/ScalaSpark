package com.sinobest.spark.basic

/**
 * 可以在Object里面通过特定的方法去创建类的实例，就是apply工厂方法去创建类
 * Object中的apply方式是class对象生成的工厂方法
 * 很多框架的代码 直接调用抽象类的apply生成实现类的实例对象
 * ，apply具有类的一切权利，抽象类不可以直接实例化，在apply方法中可实现抽象类的实例化抽象类的子类，比如源码中的Graph
 * 作用：更加能代表版本的迭代或者修改变化，这是更高意义的面向接口编程
 *
 * 类里面添加了构造参数，那么apply工厂方法也需要对应的构造参数
 * Scala中在Object中构造很多apply构造工厂方法
 * 无参的apply可以调用有参的工厂方法，可以在大数据开发中提供默认构造参数值
 *
 */
class OOPDemo(age: Int) {
  var name = "Spark"

  def sayHello = println("Hi My Name is " + name + " My'm age is " + age)

}

object OOPDemo {
  var number = 0

  def main(args: Array[String]): Unit = {
    println("Hello Scala OPP")

    //    var helloOOP = new OOPDemo
    var oopDemo = OOPDemo(30)
    var oopDemo1 = OOPDemo()
    oopDemo1.sayHello
    oopDemo.sayHello
    OOPDemo(20)

  }

  def apply(): OOPDemo = {
    println("My number is : " + number)
    number += 1
    new OOPDemo(10)
  }

  def apply(age: Int): OOPDemo = {
    println("My number is : " + number)
    number += 1
    new OOPDemo(age)
  }
}