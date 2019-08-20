package com.sinobest.spark.sql.hive

import java.net.URLDecoder

import com.sinobest.spark.{PublicUtil, SparkInit}
import org.apache.spark.sql.SparkSession

/**
  * @author liulv 
  * @date 2019/8/21
  * @time 4:27
  *
  * @description 数据质量-SparkSQL 本地模式初始化测试表
  */
//noinspection ScalaDocUnknownTag
object SjzlTestTable {

  def main(args: Array[String]): Unit = {
    val spark = SparkInit.sparkInitLocal("SjzlTestTableInit")

    testTableInit(spark)

    SparkInit.stop(spark)
  }

  /**
    * 本地运行模式，创建数据质量测试表插入测试数据
    */
  private[hive] def testTableInit(spark : SparkSession) : Unit = {

    import spark.implicits._
    import spark.sql

    //drop table if exists
    sql("drop table IF EXISTS ODS_CRJ_CRJJL_TEXT")

    //create table
    sql("create table ODS_CRJ_CRJJL_TEXT(systemid string,rksj timestamp,wybs string,xm string,zjhm string,RYLBDM string,GJDQDM string)" +
      "ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe' WITH SERDEPROPERTIES ( 'field.delim'=',','line.delim'='\n', 'serialization.format'=',')")

    //load date into table
    val classPath = PublicUtil.getClassPathFile
    val peopleJsonPath = classPath + "ODS_CRJ_CRJJL.txt"
    sql("LOAD DATA LOCAL INPATH '" + peopleJsonPath + "' INTO TABLE ODS_CRJ_CRJJL_TEXT")

  }
}
