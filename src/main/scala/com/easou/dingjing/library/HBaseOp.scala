/*************************************************************************
> FileName: ./src/main/scala/com/easou/dingjing/library/HBaseOp.scala
> Author  : DingJing
> Mail    : dingjing@live.cn
> Created Time: 2018年05月17日 星期四 09时56分28秒
 ************************************************************************/
package com.easou.dingjing.library

import java.util.{ArrayList, List}
import java.io.{FileWriter, IOException}

import scala.collection.mutable.{Map}
import scala.collection.JavaConversions._

import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{HBaseConfiguration, KeyValue=>HKV}
import org.apache.hadoop.hbase.client.{Result, ResultScanner, Scan, HTable}

import com.easou.dingjing.library.{KeyValue=>JKV}

class HBaseOp {
  private var hbaseconf = HBaseConfiguration.create()
  hbaseconf.set("hbase.zookeeper.quorum", "moses.namenode01,moses.datanode10,moses.datanode11,moses.datanode12,moses.datanode13")
  hbaseconf.set("hbase.zookeeper.property.clientPort", "2181")
  private var htable: HTable = null
  private var resultList = new ArrayList[Map[String, String]]()
  
  def apply() = {
    hbaseconf = HBaseConfiguration.create()
    hbaseconf.set("hbase.zookeeper.quorum", "moses.namenode01,moses.datanode10,moses.datanode11,moses.datanode12,moses.datanode13")
    hbaseconf.set("hbase.zookeeper.property.clientPort", "2181")
    resultList = new ArrayList[Map[String, String]]()
  }

  def scanResult(tableName: String, family: String, filed: Seq[String]): HBaseOp = {

    var scan: Scan = null
    var scanner: ResultScanner = null
    try {
      htable = new HTable(hbaseconf, tableName)
      scan = new Scan()
      for (i <- filed.toList) {
        scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(i))
      }
      scanner = htable.getScanner(scan)
      var flag = true
      while(flag) {
        val res = scanner.next()
        if (res != null) {
          var resultLine = Map[String, String]()
          resultLine("rowkey") = Bytes.toString(res.getRow())
          for (kv <- res.list()) {
            resultLine(Bytes.toString(kv.getQualifier())) = Bytes.toString(kv.getValue())
          }
          resultList += resultLine
        } else {
          flag = false
        }
      }
    } catch {
      case ex: IOException => {println("error: " + ex.getMessage())}
    } finally {
      scanner.close()
    }

    return this
  }


  def scanToFile (path: String, tableName: String, family: String, filed: Seq[String]): Unit = {

    val jkv = new JKV()
    var scanner: ResultScanner = null
    var resultTmp = new ArrayList[Map[String, String]]()
    try {
      val fw = new FileWriter(path)
      val scan = new Scan()
      htable = new HTable(hbaseconf, tableName)
      for (i <- filed.toList) {
        scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(i))
      }
      scanner = htable.getScanner(scan)
      var flag = true
      while(flag) {
        val res = scanner.next()
        if (res != null) {
          var resultLine = Map[String, String]()
          resultLine("rowkey") = Bytes.toString(res.getRow())
          for (kv <- res.list()) {
            resultLine(Bytes.toString(kv.getQualifier())) = Bytes.toString(kv.getValue())
          }
          resultTmp += resultLine
          if (resultTmp.length >= 1000) {
            writeFile (path, resultTmp)
          }
        } else {
          flag = false
        }
      }
    } catch {
      case ex: IOException => {println("error: " + ex.getMessage())}
    } finally {
      scanner.close()
      writeFile (path, resultTmp)
    }
  }


  def writeFile(path: String, list: List[Map[String, String]]): Unit = {
    val jkv = new JKV()
    val fw = new FileWriter(path)
    try {
      for (i <- list) {
        fw.write(jkv.KVLine(i) + "\n")
      }
    } catch {
      case ex: IOException => {println(ex.getMessage())}
    } finally {
      fw.close()
    }
  }


  def writeFile(path: String): Int = {

    val jkv = new JKV()
    val fw = new FileWriter(path)
    try {
      for (i <- resultList) {
        fw.write(jkv.KVLine(i) + "\n")
      }
    } catch {
      case ex: IOException => {println(ex.getMessage())}
    } finally {
      fw.close()
    }

    return resultList.length
  }

}
