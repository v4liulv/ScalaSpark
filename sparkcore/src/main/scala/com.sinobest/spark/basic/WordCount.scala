package com.sinobest.spark.basic

import org.apache.spark.{SparkConf, SparkContext}

/**
  * WordCount 集群运行的方式
  * Spark运行分为两部分，第一部分为driver（SparkConf, SparkContext）
  * 第二部分为Worker Executer ，其他业务代码都是Executer，执行在JVM的进程的对象
  */
object WordCount {

    def main(args: Array[String]): Unit = {

    /**创建SparkConf
    设置设置本地执行，不需要安装spark集群
    比如setMaster 设置集群Master ULR,如果设置local是Spark在本地运行 **/
    val conf = new SparkConf()
    conf.setAppName("Wow, My first Spark APP in IDEA!")
    conf.setMaster("local") //本地模式

        /** 创建SparkContext对象
    SparkContext是Spark唯一的入口程序，无论是采用Scala,Java
    SParkContext的核心作业：初始化Spark运用程序所需要的核心组件
    同时还会负责SPark程序往Master注册程序等，SParkContext是Spark中最为至关重要的一个对象
    创建SparkContext通过传递SparkConf实例来定制Spark运行的具体的参数和对象
          */
    val sc = new SparkContext(conf)

    /**
      * 根据具体的来源（HDFS, HBase, Loacl, Fs, DB ,S3等）通过SparkContext来创建RDD
      *  RDD的基本有三种方式：1 根据外部的数据来源（HDFS）2 根据Scala集合 3 由其他RDD来创建
      * 数据会根据RDD划分为一系列的Partitions,分配到每个的Partition的数据属于一个Task的处理范畴
      * */
     //本地模式
     //val lines = sc.textFile("E://E-文档//D-大数据//Spark//源码//spark1.6.0//spark-1.6.0-bin-hadoop2.6//CHANGES.txt", 1) //读取本地文件，并设置为Partition
    /** 集群 通过参数进行传递HDFS路径，如果没有此路径就默认路径hdfs://hadoop04.com.cn:8020/tmp/CHANGES.txt **/
    val lines =   if (args != null && args.length == 1 ) sc.textFile(args(0))  else  sc.textFile("hdfs://hadoop01:8020/tmp/CHANGES.txt")

    //对初始的RDD
    /**第一步拆分每个单词 **/
    val words = lines.flatMap { line => line.split(" ") }

    /**在每个单词计算1的基础上根据出现次数进行累加 **/
    val pairs = words.map { word => (word, 1) }

    /**
      *  广告点击排名，通过倒序排序
      *  说明：
      *  1、reduceByKey(_ + _) 对每个出现相同的key,进行累加组合为新的组合
      *  2、map(pairs => (pairs._2, pairs._1)通过key和value互相换位置，好进行相关的key进行排序
      *  3、sortByKey(false) 对map中的key进行排序，false为倒序排序，默认为正序
      *  4、最后在一次对 key 和 value进行互换位置，2次互换为最开始的key value格式
      */
    val wordCounts = pairs.reduceByKey(_ + _).map(pairs => (pairs._2, pairs._1)).sortByKey(false).map(pairs => (pairs._2, pairs._1))

    /**
      *z注意调用collect方法，是把集群中运行结果到jvm，但是本身的jvm内存有限的，数据太多可能会出现内存溢出
      * 但是如果不添加collect的话，在集群上运行的时候是shell无法看到打印信息，spark的运行记录可以看到输出信息
      */
    wordCounts.collect.foreach(wordNumberPar => println(wordNumberPar._1 + " : " + wordNumberPar._2))

    sc.stop()

  }

}