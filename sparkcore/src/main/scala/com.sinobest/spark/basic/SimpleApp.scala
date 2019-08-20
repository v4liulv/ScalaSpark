package com.sinobest.spark.basic

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author liulv 
  * @date 2019/8/15
  * @time 14:52
  *
  * @description
  */
//noinspection ScalaDocUnknownTag
object SimpleApp {

  def main(args: Array[String]) {
    val logFile = "/user/root/kv1.txt" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application").setMaster("spark://hadoop01:7077")
      .setJars(List("file:///home/hadoop/apps/scala-spark/scala-spark.jar"))
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
    sc.stop()
  }

}
