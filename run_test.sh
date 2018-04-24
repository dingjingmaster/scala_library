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
allJar="${workPath}/jar/dingjing.jar"

itemInfoTestClass="com.easou.dingjing.library.test.ItemInfoTest"
readeventTestClass="com.easou.dingjing.library.test.ReadEventTest"

sparkConf=" --master ${sparkMaster} "

if ${debug}
then
    cd ${workPath}
    rm jar -fr && mkdir jar
    sbt package
    mv ${workPath}/target/scala-*/*.jar ${allJar}
    rm ${workPath}/target -fr
    rm ${workPath}/project -fr
fi

cd ${workPath}
#spark-submit --class ${itemInfoTestClass} ${sparkConf} "${allJar}" "${itemInfoPath}"                                # 测试iteminfo解析
spark-submit --class ${readeventTestClass} ${sparkConf} "${allJar}" "${readeventPath}"                              # 测试readevent解析
mv ${allJar} ${myJar}
rm jar -fr

