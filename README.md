## 常用scala功能的封装
### ItemInfo.scala 解析hadoop物品信息
#### > func parseLine(line : String, token : String="\\t")      ----------------> 解析hadoop物品信息
#### > func getKeys() : List[String]      --------------------------------------> 获取所有key
#### > func getValue(key : String) : String      -------------------------------> 获取指定键的值
