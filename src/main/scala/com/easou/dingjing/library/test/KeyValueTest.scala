/*************************************************************************
> FileName: KeyValueTest.scala
> Author  : DingJing
> Mail    : dingjing@live.cn
> Created Time: 2018年05月16日 星期三 11时58分08秒
 ************************************************************************/
package com.easou.dingjing.library.test;

import scala.collection.mutable.Map

import com.easou.dingjing.library.{KeyValueLine => KV};

object KeyValueTest{
  def main (args : Array[String]) : Unit = {
    val kv = new KV()
    //val line = kv.KVLine(Map[String, String]("key1"->"value1", "key2"->"value2"))
    val line = kv.KVLine(Map[String, String]())
    println(line)
    println(kv.parseLine(line).getValues(List("key1", "key2")))
  }
}


