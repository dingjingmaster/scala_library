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

allTestJar="${workPath}/test/allTest.jar"

itemInfoTestClass="com.easou.dingjing.test.ItemInfoTest"
readeventTestClass="com.easou.dingjing.test.ReadEventTest"

sparkConf=" --master ${sparkMaster} "

if ${debug}
then
    cd ${workPath}
    rm test -fr && mkdir test
    sbt package
    mv ${workPath}/target/scala-*/*.jar ${allTestJar}
    rm ${workPath}/target -fr
    rm ${workPath}/project -fr
fi

cd ${workPath}
#spark-submit --class ${itemInfoTestClass} ${sparkConf} "${allTestJar}" "${itemInfoPath}"                                # 测试iteminfo解析
spark-submit --class ${readeventTestClass} ${sparkConf} "${allTestJar}" "${readeventPath}"                              # 测试iteminfo解析


