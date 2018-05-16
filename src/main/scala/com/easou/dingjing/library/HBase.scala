/*************************************************************************
> FileName: com/easou/dingjing/library/HBase.scala
> Author  : DingJing
> Mail    : dingjing@live.cn
> Created Time: 2018年04月26日 星期四 11时18分37秒
 ************************************************************************/

package com.easou.dingjing.library

import java.io.{FileWriter=>JFileWriter}
import java.util.{List=>JList, ArrayList=>JArrayList}

import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{HBaseConfiguration, KeyValue}
import org.apache.hadoop.hbase.client.{Result, ResultScanner, Scan, HTable}

class HBase() {
  private var table: String = ""
  private var conf: Configuration = HBaseConfiguration.create();
  private var list: JList[String] = new JArrayList[String]();

  def setConf(key: String, value: String): HBase = {
    conf.set(key, value)
    return this;
  }


  private def writeFile(write: JFileWriter, line: String): Unit = {
  }



  def scanToFile(tableName: List[String], family: String, field:List[String], filePath: String): Unit = {

  }
}
