package com.sinobest.scala.basices

/**
 * objeck作为Scala中的关键字，相当于java中的public static class,也就是成员都是静态的
 * 所以我们的main方法就是静态的，不需要实例就可以被jvm调用
 * object是Scala中静态类，不是对象
 */
object HelloScala {
  /*
   * def是scala的关键字，所有用def定义的函数或方法，这里main是人口方法，因为被def定义
   * Unit 表明入口方法的类型，也就是main返回的Unit类型，相当于java中void类型
   * = 表明main方法执行的结果是由谁来赋值，也就是表明main方法的执行体在那里
   * 查看源码跟踪println方法，是调用了java的io操作，也就是Scala调用了java
   * 如果方法或者返回类型或者返回是Unit就可以直接不需要写 ：Unit = ,可直接去掉，其他不能去掉
   */
  def main(args: Array[String]): Unit = {
    println("Hello Scala !!!")
    println(args.length)
  }
}