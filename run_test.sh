#!/bin/bash
source ~/.bashrc
source ~/.bash_profile

debug=true
sparkMaster='local'
workPath=$(cd $(dirname $0); pwd)  
today=`date -d "-0 day" +%Y-%m-%d`
itemInfoPath=`hadoop fs -ls "hdfs://10.26.26.145:8020/rs/iteminfo/${today}/item_*" | tail -n 1 | awk -F' ' '{print $8}'`

itemInfoTestJar="${workPath}/test/ItemInfoTest.jar"
itemInfoTestClass="com.easou.dingjing.test.ItemInfoTest"

sparkConf=" --class ${itemInfoTestClass} --master ${sparkMaster} "

if ${debug}
then
    cd ${workPath}
    rm test -fr && mkdir test
    sbt package
    mv ${workPath}/target/scala-*/*.jar ${itemInfoTestJar}
    rm ${workPath}/target -fr
    rm ${workPath}/project -fr
fi

cd ${workPath}
spark-submit ${sparkConf} "${itemInfoTestJar}" "${itemInfoPath}"                       # 测试iteminfo解析


