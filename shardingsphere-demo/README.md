## 技术版本
> Springboot 2.3.1.RELEASE
>
> ShardingSphere 4.0.0-RC1
>
> mybatis-plus-boot-starter  3.3.2

## 水平分表

### 执行sql脚本

```mysql
classpath: /script/user_db.sql
```

### application.yml

```yaml
# 水平分表的配置
# 数据源名称，多数据源以逗号分隔
spring.shardingsphere.datasource.names=ds0

# 一个bean对应多个表
spring.main.allow-bean-definition-overriding=true

# 数据源配置
spring.shardingsphere.datasource.ds0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.ds0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.ds0.url=jdbc:mysql://localhost:3306/user_db?useUnicode=true&characterEncoding=utf8\
  &useSSL=false&serverTimezone=Asia/Shanghai
spring.shardingsphere.datasource.ds0.username=root
spring.shardingsphere.datasource.ds0.password=123456

# t_user是一个虚拟表名，和mybatis bean设置的表名称一致
spring.shardingsphere.sharding.tables.t_user.actual-data-nodes=ds0.t_user_$->{1..2}
spring.shardingsphere.sharding.tables.t_user.table-strategy.inline.sharding-column=id
spring.shardingsphere.sharding.tables.t_user.table-strategy.inline.algorithm-expression=t_user_$->{id % 2 + 1}
spring.shardingsphere.sharding.tables.t_user.key-generator.column=id
spring.shardingsphere.sharding.tables.t_user.key-generator.type=SNOWFLAKE

# 绑定表规则列表
spring.shardingsphere.sharding.binding-tables=t_user

#是否开启SQL显示，默认值: false
spring.shardingsphere.props.sql.show=true
```

## 垂直分表

```mysql
classpath: /script/course_db_1.sql
classpath: /script/course_db_2.sql
```

### application.yml

```yaml
# 数据分片
# 数据源名称，多数据源以逗号分隔
spring.shardingsphere.datasource.names=ds0,ds1

# 一个bean对应多个表
spring.main.allow-bean-definition-overriding=true

spring.shardingsphere.datasource.ds0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.ds0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.ds0.url=jdbc:mysql://localhost:3306/course_db_1?useUnicode=true&characterEncoding=utf8\
  &useSSL=false&serverTimezone=Asia/Shanghai
spring.shardingsphere.datasource.ds0.username=root
spring.shardingsphere.datasource.ds0.password=123456

spring.shardingsphere.datasource.ds1.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.ds1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.ds1.url=jdbc:mysql://localhost:3306/course_db_2?useUnicode=true&characterEncoding=utf8\
  &useSSL=false&serverTimezone=Asia/Shanghai
spring.shardingsphere.datasource.ds1.username=root
spring.shardingsphere.datasource.ds1.password=123456

spring.shardingsphere.sharding.tables.t_course.actual-data-nodes=ds$->{0..1}.t_course_$->{1..2}
spring.shardingsphere.sharding.tables.t_course.table-strategy.inline.sharding-column=id
spring.shardingsphere.sharding.tables.t_course.table-strategy.inline.algorithm-expression=t_course_$->{id % 2 + 1}
spring.shardingsphere.sharding.tables.t_course.key-generator.column=id
spring.shardingsphere.sharding.tables.t_course.key-generator.type=SNOWFLAKE
spring.shardingsphere.sharding.binding-tables=t_course

spring.shardingsphere.sharding.default-database-strategy.inline.sharding-column=user_id
spring.shardingsphere.sharding.default-database-strategy.inline.algorithm-expression=ds$->{user_id % 2}

#是否开启SQL显示，默认值: false
spring.shardingsphere.props.sql.show=true
```

## 公共表的设置

每个库都创建字典表，这样的表就是公共表，可以直接连接查询

### 执行sql脚本

```mysql
classpath: /script/t_dict.sql
```

### application.yml

```yaml
#公共表的配置，比如字典表，公共表常用作关联查询，所以每个库中都存放一个公共字典表
spring.shardingsphere.sharding.broadcast-tables=t_dict
spring.shardingsphere.sharding.tables.t_dict.key-generator.column=id
spring.shardingsphere.sharding.tables.t_dict.key-generator.type=SNOWFLAKE
```

## 读写分离

### application.yml

```yaml
#数据切片+读写分离
spring.shardingsphere.datasource.names=master0,master1,master0slave0,master1slave0

# 一个bean对应多个表
spring.main.allow-bean-definition-overriding=true

#主机数据库user_db_1配置
spring.shardingsphere.datasource.master0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.master0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.master0.url=jdbc:mysql://192.168.126.20:3306/user_db_1
spring.shardingsphere.datasource.master0.username=root
spring.shardingsphere.datasource.master0.password=123456

#从机数据库user_db_1配置
spring.shardingsphere.datasource.master0slave0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.master0slave0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.master0slave0.url=jdbc:mysql://192.168.126.10:3306/user_db_1
spring.shardingsphere.datasource.master0slave0.username=root
spring.shardingsphere.datasource.master0slave0.password=123456

#主机数据库user_db_2配置
spring.shardingsphere.datasource.master1.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.master1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.master1.url=jdbc:mysql://192.168.126.20:3306/user_db_2
spring.shardingsphere.datasource.master1.username=root
spring.shardingsphere.datasource.master1.password=123456

#从机数据库user_db_2配置
spring.shardingsphere.datasource.master1slave0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.master1slave0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.master1slave0.url=jdbc:mysql://192.168.126.10:3306/user_db_2
spring.shardingsphere.datasource.master1slave0.username=root
spring.shardingsphere.datasource.master1slave0.password=123456

spring.shardingsphere.sharding.tables.t_user.actual-data-nodes=ds$->{0..1}.t_user_$->{0..1}
spring.shardingsphere.sharding.tables.t_user.table-strategy.inline.sharding-column=id
spring.shardingsphere.sharding.tables.t_user.table-strategy.inline.algorithm-expression=t_user_$->{id % 2 + 1}
spring.shardingsphere.sharding.tables.t_user.key-generator.column=id
spring.shardingsphere.sharding.tables.t_user.key-generator.type=SNOWFLAKE
spring.shardingsphere.sharding.binding-tables=t_user

spring.shardingsphere.sharding.default-database-strategy.inline.sharding-column=role_id
spring.shardingsphere.sharding.default-database-strategy.inline.algorithm-expression=master$->{role_id % 2}

spring.shardingsphere.sharding.master-slave-rules.ds0.master-data-source-name=master0
spring.shardingsphere.sharding.master-slave-rules.ds0.slave-data-source-names=master0slave0
spring.shardingsphere.sharding.master-slave-rules.ds1.master-data-source-name=master1
spring.shardingsphere.sharding.master-slave-rules.ds1.slave-data-source-names=master1slave0

spring.shardingsphere.props.sql.show=true
```

