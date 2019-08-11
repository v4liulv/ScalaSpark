package com.sinobest.spark.basic

import java.text.SimpleDateFormat
import java.util.Date

/**
  * @author liulv 
  * @date 2019/8/10
  * @time 19:41
  *
  * @description 本地开发提交Scala程序作业到远程Spark
  */
//noinspection ScalaDocUnknownTag
object SubmitScalaJobToSpark {

  def main(args: Array[String]): Unit = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss")
    val filename = dateFormat.format(new Date())
    var tmp = Thread.currentThread.getContextClassLoader.getResource("").getPath
    tmp = tmp.substring(0, tmp.length - 8)

    val arg0 = Array[String]("--master", "spark://node101:7077",
      "--deploy-mode", "client",
      "--name", "test java submit job to spark",
      "--class", "Scala_Test", "--executor-memory", "1G",
      //              "spark_filter.jar",
      tmp + "lib/spark_filter.jar", //
      "hdfs://node101:8020/user/root/log.txt",
      "hdfs://node101:8020/user/root/badLines_spark_" + filename)

    println(tmp)
    import org.apache.spark.deploy.SparkSubmit
    SparkSubmit.main(arg0)

  }
}
