/*************************************************************************
> FileName: com/easou/dingjing/ItemInfo.scala
> Author  : DingJing
> Mail    : dingjing@live.cn
> Created Time: 2018年04月17日 星期二 14时46分06秒
 ************************************************************************/
package com.easou.dingjing
import scala.collection.mutable.Map;
import scala.collection.mutable.ListBuffer;

class ItemInfo() {

  private var gid : String = "";
  private var map = Map[String, String]();
  private var keys = ListBuffer[String]();


  def parseLine(line : String, token : String="\\t") : Unit = {
    var linea = line.replace("\\r", "");
    linea = linea.replace("\\n", "");

    val arr = line.split(token);
    var i = 0;
    gid = arr(i)

    i = 2;
    while(i < arr.size) {
      map(arr(i - 1)) = arr(i);
      keys += arr(i - 1)
      i += 2;
    }
  }


  def getKeys() : List[String] = {
    return keys.toList;
  }


  def getValue(key : String) : String = {
    var value = "";
    try {
      value = map(key);
    } catch {
      case ex : Throwable => println("查询的key " + key + " 不合理");
    }

    return value;
  }

}
