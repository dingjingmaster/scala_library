#!/bin/bash
source ~/.bashrc
source ~/.bash_profile

debug=true
sparkMaster='local'
workPath=$(cd $(dirname $0); pwd)  
today=`date -d "-0 day" +%Y-%m-%d`
yesterday=`date -d "-1 day" +%Y-%m-%d`
itemInfoPath=`hadoop fs -ls "hdfs://10.26.26.145:8020/rs/iteminfo/${today}/item_*" | tail -n 1 | awk -F' ' '{print $8}'`
readeventPath="hdfs://10.26.29.210:8020/user/hive/warehouse/event_info.db/b_read_chapter/ds=${yesterday}/*"

myJar="/data/jar/"
allTestJar="${workPath}/test/allTest.jar"

#itemInfoTestClass="com.easou.dingjing.ItemInfoTest"
#readeventTestClass="com.easou.dingjing.ReadEventTest"

sparkConf=" --master ${sparkMaster} "

cd ${workPath}
sbt package
#spark-submit --class ${itemInfoTestClass} ${sparkConf} "${allTestJar}" "${itemInfoPath}"                                # 测试iteminfo解析
#spark-submit --class ${readeventTestClass} ${sparkConf} "${allTestJar}" "${readeventPath}"                              # 测试readevent解析
