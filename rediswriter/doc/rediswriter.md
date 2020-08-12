### 1 功能说明

####  1.1 配置样例

- 配置一个从Mysql数据库同步抽取数据到Redis的作业：

  ````json
  {
      "job": {
          "setting": {
              "speed": {
                  "channel": 1
              }
          },
          "content": [
              {
                   "reader": {
                      "name": "mysqlreader",
                      "parameter": {
                          "username": "root",
                          "password": "abc#123",
                          "connection": [
                              {
                                  "querySql": [
                                      "select id, name from table1 where id < 5"
                                  ],
                                  "jdbcUrl": [
                                      "jdbc:mysql://192.168.33.205:3306/odps_new_dev"
                                  ]
                              }
                          ]
                      }
                  },
                  "writer": {
                      "name": "rediswriter",
                      "parameter": {
                          "redisMode": "cluster",
                          "address": "192.168.33.205:6379, 192.168.33.205:6380, 192.168.33.205:6381, 192.168.33.206:6382, 192.168.33.206:6383, 192.168.33.206:6384",
                          "writeType": "string",
                          "config": {
                              "colKey": {
                                  "name": "id",
                                  "index": 0
                              },
                              "colValue": {
                                  "name":"name",
                                  "index": 1
                              },
                              "expire": 300,
                              "keyPrefix": "datax:string"
                          }
                      }
                  }
              }
          ]
      }
  }
  ````

  

#### 1.2 参数说明

- <b>redisMode</b>
  - 描述：cluster、singleton
  - 必选：是
  - 默认值：无
- <b>address</b>
  - 描述：redis连接地址
  - 必选：是
  - 默认值：无
- <b>auth</b>
  - 描述：redis连接密码
  - 必选：否
  - 默认值：无
- <b>writeMode</b>
  - 描述：操作类型，delete和insert
  - 必选：否
  - 默认值：insert
- <b>writeType</b>
  - 描述：对应redis的数据类型，目前支持四种: string,list,hash,geo
  - 必选：是
  - 默认值：无
- <b>strKey</b>
  - 描述：自定义的redis key值
  - 必选：否
  - 默认值：无
- <b>colKey</b>
  - 描述：对应redis key值的列配置（数据库列名name，数据库列索引index）
  - 必选：否
  - 默认值：无
- <b>colValue</b>
  - 描述：对应redis key值的列配置（hash：数据库列名name，数据库列索引index，geo：geoadd参数名名，数据库列索引index）
  - 必选：否
  - 默认值：无
- <b>expire</b>
  - 描述：redis key值的过期时间,单位秒
  - 必选：否
  - 默认值：2147483647
- <b>keyPrefix</b>
  - 描述：redis key值的前缀
  - 必选：否
  - 默认值：无
- <b>keySuffix</b>
  - 描述：redis key值的后缀
  - 必选：否
  - 默认值：无
- <b>batchSize</b>
  - 描述：批量写入的数量
  - 必选：否
  - 默认值：1000
- <b>pushType</b>
  - 描述：redis list类型的push类型，有lpush，rpush，overwrite
  - 必选：否
  - 默认值：overwrite
- <b>valueDelimiter</b>
  - 描述：redis list类型对应数据源column值的分隔符,只支持string类型的数据源column
  - 必选：否
  - 默认值：无
- <b>hashFields</b>
  - 描述：hash类型要删除的field，此参数只对删除hash类型的field时有效
  - 必选：否
  - 默认值：无
