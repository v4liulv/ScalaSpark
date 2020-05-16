package com.tyx.scala.typesystem

/**
 * Scala类型编程：
 * 1 Scala的类和方法、函数都可以是泛型，在实例话的时候指定具体的类型
 * 2 上边界表达泛型的类型必须是某种类型或者其类型的子类，语法为<:
 * 3 下边界，表达泛型类型必须是某种类型或者某类型的父类，比如办理入学手续的时候，必须是你或者你父母
 * 4 View Bounds，可以进行某种神秘的转换，把你的类型可以在没有直觉的情况进行转换
 * 例如：T <% Writable,这个代码表达的是T必须是Writable类型，但是T又没有直接继承Wirtable,使用impliciy转换为Weiyable
 * 类的泛型
 * 5 T：CLassTag其实也是一种类型转换，这是在编译的时类信息不够，需要借助于JVM的runtime来通过运行时候获取完整的信息
 * 6 逆变和斜变 -T 和 +
 */

class Engineer

class Expert extends Engineer

class Meeting[-T] 

class Animal[T](val species: T) {
  //方法的泛型
  def getAnimal(species: T): T = species
}

//人
class Person(val name: String){
  def talk(person: Person){
    println(this.name + " : " + person.name)
  }
}

//工人
class Worker(name: String) extends Person(name)
class Dog(val name: String)

//视图边界，可以进行某种神秘的转换，把你的类型可以在没有直觉的情况进行转换
//例如：T <% Writable,这个代码表达的是T必须是Writable类型，但是T又没有直接继承Wirtable,使用impliciy转换为Weiyable类的泛型
class Clubl[T <% Person](p1: T, p2: T){
  def comunicate(): Unit = p1.talk(p2)
}

//上边界表达泛型的类型必须是某种类型或者其类型的子类，语法为<:
class Club[T <:Person](p1: T, p2: T){
  def comunicate(): Unit = p1.talk(p2)
}


object TypeSystem {

  def main(args: Array[String]): Unit = {
   implicit def dog2Person(dog: Dog): Person = new Person(dog.name)
   val p = new Person("Scala")
   val w = new Worker("Sparlk")
   val dog = new Dog("dahuang")
   new Club(p, w).comunicate()
   new Clubl[Person](w, dog).comunicate()

   val e = new Meeting[Engineer] 
   participateMeeting(e)
   
   val expert: Meeting[Expert] = new Meeting[Expert]
   participateMeeting(e)
   
  }
  
  def participateMeeting(meeting:Meeting[Engineer]){
    println("Wecome")
  }

}