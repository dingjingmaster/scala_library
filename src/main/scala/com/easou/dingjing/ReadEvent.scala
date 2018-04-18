/*************************************************************************
> FileName: com/easou/dingjing/ReadEvent.scala
> Author  : DingJing
> Mail    : dingjing@live.cn
> Created Time: 2018年04月17日 星期二 14时46分06秒
 ************************************************************************/
package com.easou.dingjing
import scala.collection.mutable.Map;
import scala.collection.mutable.ListBuffer;

class ReadEvent() {

  private var field = Map("0" -> "os",
                        "1" -> "phone_udid2",
                        "2" -> "phone_softversion",
                        "3" -> "last_cpid",
                        "4" -> "package_name",
                        "5" -> "appkey",
                        "6" -> "sdk_version",
                        "7" -> "cpid",
                        "8" -> "currentnetworktype",
                        "9" -> "type",
                        "10" -> "phone_imei",
                        "11" -> "phone_apn",
                        "12" -> "phone_udid",
                        "13" -> "gatewayip",
                        "14" -> "phone_mac",
                        "15" -> "phone_imsi",
                        "16" -> "phone_city",
                        "17" -> "src_code",
                        "18" -> "status",
                        "19" -> "time",
                        "20" -> "event_id");


  def parseLine(line : String) : Unit = {
    var linea = line.replace("\\r", "");
    linea = linea.replace("\\n", "");
    val arr = line.split("\\x01");
    for (i <- 0 to 20) {
      val key = field(i.toString());
      field(key) = arr(i).trim();
      field -= (i.toString())
    }

    val para = arr(21).split("\\x02")
    for (i <- para) {
      val itemArr = i.split("\\x03")
      field(itemArr(0).trim()) = itemArr(1).trim();
    }

    field("server_time") = arr(22)
  }


  def getKeys() : Iterable[String] = {
    return field.keys;
  }


  def getValue(key : String) : String = {
    var value = "";
    try {
      value = field(key);
    } catch {
      case ex : Throwable => println("查询的key " + key + " 不合理");
    }

    return value;
  }

  def getValues(keys : List[String]) : List[String] = {
    var res = ListBuffer[String]();
    if (! keys.isEmpty) {
      for (i <- keys) {
        res += getValue(i.trim());
      }
    }

    return res.toList;
  }

}
