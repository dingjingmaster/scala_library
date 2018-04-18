## 常用scala功能的封装 [![Build Status](https://travis-ci.org/dingjingmaster/scala_library.svg?branch=master)](https://travis-ci.org/dingjingmaster/scala_library)
### 1.ItemInfo.scala 解析hadoop物品信息
> 1. func parseLine(line : String, token : String="\\t")      ----------------> 解析hadoop物品信息
> 2. func getKeys() : List[String]      --------------------------------------> 获取所有key
> 3. func getValue(key : String) : String      -------------------------------> 获取指定键的值
