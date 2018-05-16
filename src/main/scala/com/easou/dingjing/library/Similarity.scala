/*************************************************************************
> FileName: com/easou/dingjing/library/Similarity.scala
> Author  : DingJing
> Mail    : dingjing@live.cn
> Created Time: 2018年04月28日 星期六 11时28分28秒
 ************************************************************************/
package com.easou.dingjing.library

import scala.math._
//import 


class Similarity {
  // 密度向量计算
  /*
  def denseCosine() {
  }
  */
  // 稀疏向量计算
  def sparseCosine(xIndex: Array[Int], yIndex: Array[Int], xValue: Array[Double], yValue: Array[Double]) {
    val xi = xIndex.toSet
    val yi = yIndex.toSet

    val xydiff = xi & yi
    val xyunion = xi | yi

    var cz: Double = 0
    var cm: Double = 0
  }
}

