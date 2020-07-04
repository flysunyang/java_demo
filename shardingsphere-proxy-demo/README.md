## 技术版本
> Springboot 2.3.1.RELEASE
>
> ShardingSphere-Proxy 4.1.0
>
> mybatis-plus-boot-starter  3.3.2

## 启动ShardingSphere-Proxy服务

### 将mysql的jar包放入lib目录中

```yaml
/lib/mysql-connector-java-8.0.20.jar
```

### 修改server.xml

```yaml
#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

######################################################################################################
# 
# If you want to configure orchestration, authorization and proxy properties, please refer to this file.
# 
######################################################################################################
#
#orchestration:
#  orchestration_ds:
#    orchestrationType: registry_center,config_center,distributed_lock_manager
#    instanceType: zookeeper
#    serverLists: localhost:2181
#    namespace: orchestration
#    props:
#      overwrite: false
#      retryIntervalMilliseconds: 500
#      timeToLiveSeconds: 60
#      maxRetries: 3
#      operationTimeoutMilliseconds: 500
#
authentication:
  users:
    root:
      password: root
    sharding:
      password: sharding
      authorizedSchemas: sharding_db

props:
  max.connections.size.per.query: 1
  acceptor.size: 16  # The default value is available processors count * 2.
  executor.size: 16  # Infinite by default.
  proxy.frontend.flush.threshold: 128  # The default value is 128.
    # LOCAL: Proxy will run with LOCAL transaction.
    # XA: Proxy will run with XA transaction.
    # BASE: Proxy will run with B.A.S.E transaction.
  proxy.transaction.type: LOCAL
  proxy.opentracing.enabled: false
  proxy.hint.enabled: false
  query.with.cipher.column: true
  sql.show: false
  allow.range.query.with.inline.sharding: false

```

### 测试

```yaml
mysql -h127.0.0.1 -P3307 -uroot -p
```

## 数据分片

### 配置修改config-sharding.yaml

```yaml
schemaName: sharding_db

dataSources:
  ds_0:
    url: jdbc:mysql://127.0.0.1:3306/user_db_1?serverTimezone=Asia/Shanghai&useSSL=false
    username: root
    password: 123456
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 50
  ds_1:
    url: jdbc:mysql://127.0.0.1:3306/user_db_2?serverTimezone=Asia/Shanghai&useSSL=false
    username: root
    password: 123456
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 50

shardingRule:
  tables:
    t_user:
      actualDataNodes: ds_${0..1}.t_user_${1..2}
      tableStrategy:
        inline:
          shardingColumn: id
          algorithmExpression: t_user_${id % 2 + 1}
      keyGenerator:
        type: SNOWFLAKE
        column: id
  bindingTables:
    - t_user
  defaultDatabaseStrategy:
    inline:
      shardingColumn: role_id
      algorithmExpression: ds_${role_id % 2}
  defaultTableStrategy:
    none:
```

### application.yml

数据源直接连接sharding-proxy

```yaml
server:
  port: 80

spring:
  datasource:
    # 数据库连接地址为 sharding-proxy 代理地址
    type: com.alibaba.druid.pool.DruidDataSource
    url: jdbc:mysql://192.168.126.20:3307/sharding_db?serverTimezone=Asia/Shanghai&useSSL=false
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
```

## 读写分离

### 修改config-master_slave.yaml

```yaml
#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

######################################################################################################
#
# Here you can configure the rules for the proxy.
# This example is configuration of master-slave rule.
#
# If you want to use master-slave, please refer to this file;
# if you want to use sharding, please refer to the config-sharding.yaml.
#
######################################################################################################
#
#schemaName: master_slave_db
#
#dataSources:
#  master_ds:
#    url: jdbc:postgresql://127.0.0.1:5432/demo_ds_master?serverTimezone=UTC&useSSL=false
#    username: postgres
#    password: postgres
#    connectionTimeoutMilliseconds: 30000
#    idleTimeoutMilliseconds: 60000
#    maxLifetimeMilliseconds: 1800000
#    maxPoolSize: 50
#  slave_ds_0:
#    url: jdbc:postgresql://127.0.0.1:5432/demo_ds_slave_0?serverTimezone=UTC&useSSL=false
#    username: postgres
#    password: postgres
#    connectionTimeoutMilliseconds: 30000
#    idleTimeoutMilliseconds: 60000
#    maxLifetimeMilliseconds: 1800000
#    maxPoolSize: 50
#  slave_ds_1:
#    url: jdbc:postgresql://127.0.0.1:5432/demo_ds_slave_1?serverTimezone=UTC&useSSL=false
#    username: postgres
#    password: postgres
#    connectionTimeoutMilliseconds: 30000
#    idleTimeoutMilliseconds: 60000
#    maxLifetimeMilliseconds: 1800000
#    maxPoolSize: 50
#
#masterSlaveRule:
#  name: ms_ds
#  masterDataSourceName: master_ds
#  slaveDataSourceNames:
#    - slave_ds_0
#    - slave_ds_1

######################################################################################################
#
# If you want to connect to MySQL, you should manually copy MySQL driver to lib directory.
#
######################################################################################################

#逻辑库名称，需要和server.yaml保持一致，数据库连接库名也要和它保持一致
schemaName: sharding_master_slave_db

dataSources:
  ds0:
    url: jdbc:mysql://192.168.126.20:3306/user_db_1?serverTimezone=Asia/Shanghai&useSSL=false
    username: root
    password: 123456
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 65
  ds0_slave0:
    url: jdbc:mysql://192.168.126.10:3306/user_db_1?serverTimezone=Asia/Shanghai&useSSL=false
    username: root
    password: 123456
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 65
  ds1:
    url: jdbc:mysql://192.168.126.20:3306/user_db_2?serverTimezone=Asia/Shanghai&useSSL=false
    username: root
    password: 123456
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 65
  ds1_slave0:
    url: jdbc:mysql://192.168.126.10:3306/user_db_2?serverTimezone=Asia/Shanghai&useSSL=false
    username: root
    password: 123456
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 65

shardingRule:
  tables:
    t_user:
      actualDataNodes: ms_ds${0..1}.t_user_${1..2}
      databaseStrategy:
        inline:
          shardingColumn: role_id
          algorithmExpression: ms_ds${role_id % 2}
      tableStrategy:
        inline:
          shardingColumn: id
          algorithmExpression: t_user_${id % 2 + 1}
      keyGenerator:
        type: SNOWFLAKE
        column: id
  bindingTables:
  - t_user

  defaultDataSourceName: ds0
  defaultTableStrategy:
    none:

  masterSlaveRules:
    ms_ds0:
      masterDataSourceName: ds0
      slaveDataSourceNames:
      - ds0_slave0
      loadBalanceAlgorithmType: ROUND_ROBIN
    ms_ds1:
      masterDataSourceName: ds1
      slaveDataSourceNames:
      - ds1_slave0
      loadBalanceAlgorithmType: ROUND_ROBIN
```

