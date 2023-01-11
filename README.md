# 工程简介
## com.practice.ticket
Redis + kafka + mysql 抢购

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
## com.practice.algorithm.search.erfen 二分算法
## com.practice.auth jwt认证
## com.practice.classloader classloader 测试
## com.practice.cruddb 数据库操作工具
## com.practice.customconfig 自定义yaml文件测试
## com.practice.excel excel 导出
## com.practice.javacv javacv 处理视频流
## com.practice.jdbcTemplate jdbcTemplate
## com.practice.mail 邮件发送
## com.practice.mybatisplus mybatisplus 配置、批量操作
## com.practice.proxy 动态代理
## com.practice.quartz quartz定时任务
## com.practice.redis redisTemplate
## com.practice.springlearn.ioc springioc 学习
## com.practice.test 测试工具
## com.practice.threadlocal ThreadLocal实践
## com.practice.tree tree数据工具类
## com.practice.typehandler mybatis/mybatisplus typehandler
## com.practice.websocket 