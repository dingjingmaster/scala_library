/*************************************************************************
> FileName: KeyValue.scala
> Author  : DingJing
> Mail    : dingjing@live.cn
> Created Time: 2018年05月16日 星期三 11时10分46秒
 ************************************************************************/
package com.easou.dingjing.library;

import scala.collection.mutable.Map;
import scala.collection.mutable.ListBuffer;

class KeyValueLine {
  private val kkToken: Char = '\02'
  private val kvToken: Char = '\03'
  private var map = Map[String, String]();

  def parseLine(line: String): KeyValueLine = {
    var linea = line.replace("\\r", "")
    linea = linea.replace("\\n", "")
    val arr: Array[String] = linea.split(kkToken)
    for (x <- arr) {
      val kvarr = x.split(kvToken)
      if (kvarr.length > 1) {
        map(kvarr(0).trim()) = kvarr(1)
      } else {
        map(kvarr(0).trim()) = ""
      }
    }
    return this
  }


  def getKeys(): Iterable[String] = {
    return map.keys
  }


  def getValue(key: String): String = {
    var value = ""
    try {
      value = map(key)
    } catch {
      case ex: Throwable => println("key: " + key + " 不存在")
    }
    return value
  }


  def getValues(keys: List[String]): List[String] = {
    var res = ListBuffer[String]()
    if(! keys.isEmpty) {
      for (i <- keys) {
        res += getValue(i.trim())
      }
    }
    return res.toList
  }


  def KVLine(map: Map[String, String]): String = {
    var line = ""
    if (map.size > 0) {
      map.foreach(l => {
        val (key, value) = l 
        line += key + kvToken + value + kkToken
      })
    } 
    return line.dropRight(1)
  }
}
