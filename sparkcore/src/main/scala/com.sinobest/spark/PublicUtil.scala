package com.sinobest.spark

import java.net.URLDecoder

/**
  * @author liulv 
  * @date 2019/8/21
  * @time 4:48
  * @description 公共的工具类
  */
//noinspection ScalaDocUnknownTag
object PublicUtil {

  /**
    * 获取classpath路径
    *
    * @return 返回classpath路径
    */
  def getClassPath: String = {
    var classPath = Thread.currentThread.getContextClassLoader.getResource("").getPath
    classPath = classPath.substring(0, classPath.length - 8)
    classPath = URLDecoder.decode(classPath, "UTF-8") + "classes/"

    classPath
  }

  /**
    * file: 模式下的classpath路径
    *
    * @return 返回 file: classpath
    */
  def getClassPathFile: String = {
   "file:" + getClassPath
  }
}
