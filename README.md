# 工程简介
1. TicketGrabController
+ Redis + kafka + mysql 实现抢票

# 延伸阅读

# 环境搭建
### docker + nacos + zookeeper + kafka 
``` java
//nacos
docker run --name nacos-quick -e MODE=standalone -e MYSQL_SERVICE_HOST=host.docker.internal -e MYSQL_SERVICE_PORT=3306 -e MYSQL_SERVICE_DB_NAME=nacos -e MYSQL_SERVICE_USER=root -e MYSQL_SERVICE_PASSWORD=123456 -e SPRING_DATASOURCE_PLATFORM=mysql -p 8848:8848 -d zill057/nacos-server-apple-silicon:2.0.3
//zookeeper
docker pull wurstmeister/zookeeper
docker run -d --name zookeeper -p 2181:2181 -t wurstmeister/zookeeper
//kafka
docker pull wurstmeister/kafka
docker run -d --name kafka --publish 9092:9092 --link zookeeper --env KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 --env KAFKA_ADVERTISED_HOST_NAME=192.168.59.101 --env KAFKA_ADVERTISED_PORT=9092 --volume /etc/localtime:/etc/localtime wurstmeister/kafka:latest
```
### mysql
``` roomsql
CREATE DATABASE `practice`;
CREATE TABLE `ticket_tel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tel` varchar(20) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29592 DEFAULT CHARSET=latin1;
```
