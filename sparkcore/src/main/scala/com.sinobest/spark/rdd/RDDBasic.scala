package com.sinobest.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by liulv on 2017/2/27.
  */
object RDDBasic {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setAppName("RDD Basic RDD create exception.." ).setMaster("local")
    val sc = new SparkContext(conf)

    val rddC = sc.textFile("G://文档//Spark//源码//spark1.6.0//spark-1.6.0-bin-hadoop2.6//README.md")

    val rddF = rddC.flatMap{line => line.split(" ")}.map{word => (word, 1)}.filter(pairs => pairs._1.equals("Spark"))

    rddF.map(pairs => if (pairs._1 equals "Spark") (pairs._1, pairs._2) else () ).foreach(println)

    sc.stop

  }

}
