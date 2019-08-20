package com.sinobest.spark

import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

/**
  * @author liulv 
  * @date 2019/8/21
  * @time 4:39
  *
  * @description 本地模式Spark初始化
  */
//noinspection ScalaDocUnknownTag
object SparkInit {
  private val LOG = LoggerFactory.getLogger(SparkInit.getClass)

  /**
    * 本地模式-初始化Spark
    *
    * @return 返回SparkSession
    */
  private[spark] def sparkInitLocal(appName : String) : SparkSession = {
    var sparkAppName = appName
    val appNameDefault = "sparkTest"
    if(sparkAppName == null || sparkAppName.trim.equals("")){
      sparkAppName = appNameDefault
      LOG.warn("------初始化Spark默认appName为空，使用默认名:{}", appNameDefault)
    }

    val spark = SparkSession.builder()
      .appName(sparkAppName)
      .master("local")
      .enableHiveSupport()
      .getOrCreate()

    spark
  }

  /**
    * 停掉底层的 `SparkContext`.
    *
    * @param spark SparkSession
    */
  private[spark] def stop(spark: SparkSession) : Unit = {
    if(spark != null)
      spark.stop()
  }

}
