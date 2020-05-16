package com.tyx.scala.function

/**
 * @author Administrator
 *
 */
class FunctionProgramming {

  /** 函数的定义为输入参数 func 对应的输入类型为  (String) ，第二个参数为name ,类型为：String **/
  def getName(func: (String) => Unit, name: String) {
    //回调方法中，来实现业务逻辑
    func(name)
  }

  def hiBigdata(name: String) {
    println("Hi " + name)
  }

  def funcResult(message: String) = (name: String) => println(message + " : " + name)

  /**
    * 其中参数可以是默认值如 age: Int = 25,在调用函数的时候可以不用传递age参数指，直接使用默认值，如 hello("hadoop")
    * @param name
    * @param age
    */
  def hello(name: String, age: Int = 25 ) ={
    println("Hello, My name is " + name)
    println("Hello, My age is " + age)
  }

  /**
    * @param n int
    * @return 返回裂变的总和
    */
  def fibonaci(n: Int): Int = {
    if (n <= 1) 1
    else fibonaci(n - 2) + fibonaci(n - 1)
  }

  /**
    * 实现for累加
    * @param numbers  (1 to 10 : _*)
    * @return numbers的每个元素的累加结果
    */
  def sum(numbers: Int*): Int ={
    var result = 0
    for(number <- numbers) result += number
    result
  }

  /**
    * sumrecursive 可变参数实现 递归累加
    * @param numbers
    * @return numbers的每个元素递归想加
    */
  def sumrecursive(numbers: Int*): Int = {
    if(0 == numbers.length) 0
    else numbers.head + sumrecursive(numbers.tail: _*)
  }

  def map(numbers: Array[Int]) = {
    numbers.map{(item: Int) => 2 * item }.foreach { x => println(x) }
  }

}

/**
 * 函数式编程学习
 */
object FunctionProgramming {

  def apply(): FunctionProgramming = new FunctionProgramming()

  def main(args: Array[String]): Unit = {

    //val hiData = hiBigdata _
    //hiData("Spark")

    //匿名函数,不需要声明函数名，直接输入
    val f = (name: String) => println("Hello  " + name)
    f("scala")

    FunctionProgramming().getName(f, "Scala")

    /** 很多集合都是上面的操作  Array自动展开方式 Array (ara: _*) */
    //Array(1 to 10: _*).map { (item: Int) => 2 * item }.foreach { x => println(x) }
    FunctionProgramming().map(Array(1 to 10: _*))

    //funcResult("Hello")("Java")
    val result = FunctionProgramming().funcResult("Hello")
    result("Java")
  }



}