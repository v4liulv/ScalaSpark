package com.sinobest.spark.sql

import java.net.URLDecoder

import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext, sql}

/**
  * @author liulv 
  * @date 2019/8/15
  * @time 15:22
  * @description
  */
//noinspection ScalaDocUnknownTag
object SparkSQLExample {

  //相当于Java实体类
  case class Person(name: String, age: Long)

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
      .setAppName("Spark SQL basic example")
      //.setMaster("spark://hadoop01:7077") //yarn-client
      .setMaster("local") //yarn-client
      //.set("deploy-mode", "cluster")

    val sc = new SparkContext(sparkConf)

    val sqlContext = new HiveContext(sc)
    import sqlContext.implicits._
    import sqlContext.sql

    runBasicDataFrameExample(sqlContext)

  }


  private def runBasicDataFrameExample(sqlContext: SQLContext): Unit = {
    var tmp = Thread.currentThread.getContextClassLoader.getResource("").getPath
    tmp = tmp.substring(0, tmp.length - 8)
    tmp = URLDecoder.decode(tmp, "UTF-8")
    val peopleJsonPath = "file:" + tmp + "classes/people.json"
    val df = sqlContext.read.json(peopleJsonPath)

    // Displays the content of the DataFrame to stdout
    df.show()

    df.printSchema()

    import sqlContext.implicits._
    df.select("name").show()

    df.select($"name", $"age" + 1).show()

    df.filter($"age" > 21).show()

    df.groupBy("age").count().show()

    df.createOrReplaceTempView("people")
    //新版本
    //df.createOrReplaceTempView("people")

    val sqlDF = sqlContext.sql("SELECT * FROM people")

  }

  private def runDatasetCreationExample(sqlContext: SQLContext): Unit = {
    import sqlContext.implicits._
    val caseClassDS = Seq(Person("Andy", 32)).toDS()

    caseClassDS.show()

    val primitiveDS = Seq(1, 2, 3).toDS()

    //
    val path = "/user/root/people.json"

    val peopleDS = sqlContext.read.json(path).as[Person]

    peopleDS.show()
  }

  private def runInferSchemaExample(sc: SparkContext): Unit = {
    implicit val mapEncoder = org.apache.spark.sql.Encoders.kryo[Map[String, Any]]

    val sqlContext = new SQLContext(sc)

    // MapPartitionsRDD
    val persionRDD =  sc.textFile("/user/root/persions.txt")
    val schemaString = "id name age"

    // Generate the schema based on the string of schema 基于schema字符串（schemaString）生成schema
    val fields = schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, nullable = true))
    val schema = StructType(fields)

    //根据读取的MapPartitionsRDD生成Row相关的MapPartitionsRDD
    val rowRDD = persionRDD.map(_.split(",")).map(attributes =>
      Row(attributes(0).trim(), attributes(1), attributes(2).trim()))

    //Apply the schema to the RDD(person) 根据Row相关的MapPartitionsRDD和shema创建DataFrame
    val personDF = sqlContext.createDataFrame(rowRDD, schema)

    //根据DataFrame创建
    personDF.registerTempTable("person")
    val results = sqlContext.sql("select * from person")

  }

}
