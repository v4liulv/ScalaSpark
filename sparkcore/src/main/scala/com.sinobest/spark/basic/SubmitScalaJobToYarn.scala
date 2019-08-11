package com.sinobest.spark.basic

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.hadoop.conf.Configuration
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.deploy.yarn.ClientArguments


/**
  * @author liulv 
  * @date 2019/8/10
  * @time 19:47
  *
  * @description 本地开发提交Scala程序作业到远程Spark
  */
//noinspection ScalaDocUnknownTag
object SubmitScalaJobToYarn {

  def main(args: Array[String]): Unit = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss")
    val filename: String = dateFormat.format(new Date())
    var tmp: String = Thread.currentThread.getContextClassLoader.getResource("").getPath
    tmp = tmp.substring(0, tmp.length - 8)
    val arg0: Array[String] = Array[String]("--name", "test java submit job to yarn",
      "--class", "Scala_Test",
      "--executor-memory", "1G",
      //              "WebRoot/WEB-INF/lib/spark_filter.jar",
      "--jar", tmp + "target/scala-spark.jar", //
      "--arg", "hdfs://hadoop01:8020/tmp/CHANGES.txt")
      //"--arg", "hdfs://node101:8020/user/root/badLines_yarn_" + filename,
      //"--addJars", "hdfs://node101:8020/user/root/servlet-api.jar",
     // "--archives", "hdfs://node101:8020/user/root/servlet-api.jar")

    //      SparkSubmit.main(arg0);
    val conf = new Configuration()
    val os: String = System.getProperty("os.name")
    var cross_platform = false
    if (os.contains("Windows")) cross_platform = true
    conf.setBoolean("mapreduce.app-submission.cross-platform", cross_platform) // 配置使用跨平台提交任务

    conf.set("fs.defaultFS", "hdfs://hadoop01:8020") // 指定namenode

    conf.set("mapreduce.framework.name", "yarn") // 指定使用yarn框架

    conf.set("yarn.resourcemanager.address", "hadoop01:8034") // 指定resourcemanager

    conf.set("yarn.resourcemanager.scheduler.address", "hadoop01:8030") // 指定资源分配器

    conf.set("mapreduce.jobhistory.address", "hadoop01:10020")

    System.setProperty("SPARK_YARN_MODE", "true")

    val sparkConf = new SparkConf().setMaster("yarn-client").setAppName("test123")
    //var cArgs = new ClientArguments(arg0, sparkConf);

    //new org.apache.spark.deploy.yarn.Client(cArgs, conf, sparkConf).run();

    val sc = new SparkContext(sparkConf)

    sc.stop()
  }
}
