/*************************************************************************
> FileName: com/easou/dingjing/library/HBase.scala
> Author  : DingJing
> Mail    : dingjing@live.cn
> Created Time: 2018年04月26日 星期四 11时18分37秒
 ************************************************************************/

package com.easou.dingjing.library

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.client.ResultScanner
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.KeyValue
import org.apache.hadoop.hbase.client.HTable
import org.apache.hadoop.hbase.util.Bytes

class HBase() {
  private var table: String = ""
  private var conf: Configuration = new HBaseConfiguration.create();

  def setConf(key: String, value: String): HBase = {
    conf.set(key, value)
    return this;
  }

  def scanToFile(key: List[String], filePath: String): Unit = {
  }
}
