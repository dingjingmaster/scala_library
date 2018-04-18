/*************************************************************************
> FileName: ItemInfoTest.scala
> Author  : DingJing
> Mail    : dingjing@live.cn
> Created Time: 2018年04月17日 星期二 15时57分35秒
 ************************************************************************/
package com.easou.dingjing.test;

import com.easou.dingjing.{ReadEvent => RE};
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object ReadEventTest{
  def main (args : Array[String]) : Unit = {
    if (args.length != 1) {
      System.exit(1);
    }
    val readPath = args(0);
    val conf = new SparkConf().setAppName("read event test")
                    .set("spark.executor.memory", "20G")
                    .set("spark.cores.max", "30");

    val sc = new SparkContext(conf);
    val test = sc.textFile(readPath)
                    .map(test_line);

    println(test.take(1)(0))
  }

  def test_line(line : String) : String = {
    var ii = new RE();
    ii.parseLine(line);
    var out = "";
    for (i <- ii.getKeys()) {
      out += i;
      out += "\t------>\t"
      out += ii.getValue(i)
      out += "\n"
    }

    return out;
  }
}


