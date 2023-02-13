#注意每个项目的名字都不一样，需要修改QUALITY_SMALL_STATION_QUALITYSMALLSTATION为自己项目的名称,否则在一个操作系统里使用同样的名字会导致互相影响
export QUALITY_SMALL_STATION_QUALITYSMALLSTATION_PORT="8090"
export QUALITY_SMALL_STATION_QUALITYSMALLSTATION_POSTGRE_URL="jdbc:postgresql://47.105.140.86:5432/single?characterEncoding=utf8&useSSL=false"
export QUALITY_SMALL_STATION_QUALITYSMALLSTATION_POSTGRE_USERNAME="postgres"
export QUALITY_SMALL_STATION_QUALITYSMALLSTATION_POSTGRE_PASSWORD="1234qwer1234asdf"
export QUALITY_SMALL_STATION_QUALITYSMALLSTATION_DRUID_PORTAL="/d1Project/druid/*"

count=$(ps -ef | grep "java" | grep "qualitysmallstation" | wc -l)
if [ $count -le 0 ]; then
echo "qualitysmallstation stop"
else
pid=$(ps aux | grep 'qualitysmallstation' |grep java| awk '{print $2}' | sort -n | head -n 1)
echo "qualitysmallstation running, kill pid:$pid"
kill $pid
fi
java  -Xms128M -Xmx512m -jar ./qualitysmallstation-0.0.1-SNAPSHOT.jar &
