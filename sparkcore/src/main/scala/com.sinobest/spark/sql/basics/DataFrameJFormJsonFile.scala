package com.sinobest.spark.sql.basics

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/11/22 0022.
  *
  * Spark	SQL能够自动推断JSON数据集的模式，加载它为一个Schema RDD
  * json File	：从一个包含JSON文件的目录中加载。文件中的每一行是一个JSON对象
  */
object DataFrameJFormJsonFile {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("DataFrameJFormJsonFile")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val peopleJsonPath = "E://E-文档//D-大数据//Spark//源码//spark1.6.0//spark-1.6.0-bin-hadoop2.6//" +
      "examples//src//main//resources//people.json"
    val people = sqlContext.jsonFile(peopleJsonPath)
    people.printSchema()
    //	root
    //		|--	age:	integer	(nullable	=	true)
    //		|--	name:	string	(nullable	=	true)

    people.registerTempTable("people")

    sqlContext.sql("SELECT * FROM people WHERE age >=13 AND age <=19").foreach(print)

    //json RDD
    //	Alternatively,	a	Schema RDD	can	be	created	for	a	JSON	dataset	represented	by
    //	an	RDD[String]	storing	one	JSON	object	per	string.
    val anotherPeopleRDD = sc.parallelize(
      """{"name":"Yin","address":{"city":"Columbus","state":"Ohio"}}""" :: Nil)
    val anotherPeople = sqlContext.jsonRDD(anotherPeopleRDD)
    anotherPeople.registerTempTable("autoPeople")
    sqlContext.sql("select * from autoPeople").foreach(print)

    sc.stop()
  }

}
