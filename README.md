# 《云计算技术》课程项目

2020 Tongji University SSE "Cloud Computing" Course  Final Project



## :hourglass:项目题目描述

- 基于***单个NameNode***或***Zookeeper实现NameNode的HA***架构模式搭建 Hadoop；
- 基于搭建好的 Hadoop，安装 Hive，并在 Hive 上创建数据库；
- 基于已安装好的 Hive，为一个销售系统的销售模块创建库和表（需要自行设计表结构，并构造一些模拟数据填充进去，以便可以用来做查询分析）



## :crystal_ball:团队成员

| <img src="https://avatars0.githubusercontent.com/u/44048421?v=4" alt="@zhizhizhii" width="100px" height="100px"/> | <img src="https://avatars.githubusercontent.com/u/44796985?&v=4" alt="@poros666" width="100px" height="100px"/> | <img src="https://avatars.githubusercontent.com/u/29847470?v=4" alt="@RAND1925" width="100px" height="100px"/> |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|         [zhizhizhii](https://github.com/zhizhizhii)          |           [poros666](https://github.com/poros666)            |           [RAND1925](https://github.com/RAND1925)            |



### 一、服务器端环境搭建

#### Hadoop集群搭建部署情况：

​		我们的Hadoop集群采用完全分布式模式，集群搭建基于三台阿里云上的服务器，分别配置Hadoop环境进行连接，同时我们通过配置Active/Standby两个NameNode、ResourceManager实现在集群中对它们的热备，也实现了HDFS和YARN的高可用，消除了单点故障，同时利用ZooKeeper故障转移控制器实现了自动故障转移，在Active节点down掉后，所有Standby节点会自动争抢成为Active节点。

- [x] 完全分布式
- [x] 使用Zookeeper集群
- [x] 集群高可用、YARN高可用、消除单点故障 HA
- [x] 使用Zookeeper集群实现自动故障转移

<center><img src=".\document_img\img_1.png" alt="img_1" style="zoom: 50%;" /></center>


|  hadoop01(阿里云)   | hadoop02(阿里云)  | hadoop03(阿里云) |
| :-----------------: | :---------------: | :--------------: |
| NameNode（Standby） | NameNode（Active) |                  |
|      DataNode       |     DataNode      |     DataNode     |
|     JournalNode     |    JournalNode    |   JournalNode    |
|         ZK          |        ZK         |        ZK        |
|                     |                   |                  |
|   ResourceManager   |  ResourceManager  |                  |
|     NodeManager     |    NodeManager    |   NodeManager    |



#### Hadoop（HA）集群启动步骤：

##### 1、启动Zookeeper集群：

```shell
[hadoop@hadoop001 ~]$ cd /opt/module/zookeeper-3.4.10/
[hadoop@hadoop001 zookeeper-3.4.10]$ bin/zkServer.sh start
```

##### 2、启动hdfs(NameNode、DataNode、JournalNode、ZK)

```shell
[hadoop@hadoop001 zookeeper-3.4.10]$ cd /opt/ha/hadoop-2.7.2/
[hadoop@hadoop001 hadoop-2.7.2]$ sbin/start-dfs.sh
```

可访问hadoop001(standby)与hadoop002(active)服务器50070端口查看HDFS是否启动成功；

##### 3、启动YARN（HA）

**3.1 在hadoop002中执行：**

```shell
[hadoop@hadoop002 ~]$ cd /opt/ha/hadoop-2.7.2/
[hadoop@hadoop002 hadoop-2.7.2]$ sbin/start-yarn.sh
```

**3.2 在hadoop001中执行：**

```shell
[hadoop@hadoop002 ~]$ cd /opt/ha/hadoop-2.7.2/
[hadoop@hadoop002 hadoop-2.7.2]$ sbin/yarn-daemon.sh start resourcemanager
```

可访问hadoop002服务器的8088端口检测yarn是否启动成功；



#### Hive部署情况：

- **以Mysql数据库（jdbc:mysql://hadoop001:3306/metastore）作为元数据库**
- 在hadoop02机器上提供hive工具，启用hiveserver2服务
- 提供JDBC接口：jdbc:hive2://hadoop002:10000

<center><img src=".\document_img\img_2.png" alt="img_2" style="zoom: 100%;" /></center>



#### Hive启动步骤：

##### 1、服务器环境测试：

```shell
[hadoop@hadoop001 ~]$ cd /opt/module/hive
[hadoop@hadoop001 hive]$ bin/hive
```

##### 2、hiveserver2服务启动

```shell
[hadoop@hadoop001 ~]$ cd /opt/module/hive
[hadoop@hadoop001 hive]$ bin/hive --service metastore&
[hadoop@hadoop001 hive]$ bin/hiveserver2&
```



### 二、模拟数据

本项目的模拟数据有三个来源

1. stanford的亚马逊电影评论数据集
2. 对亚马逊购物网站的网络爬虫
3. 基于算法生成的模拟数据

#### Stanford的亚马逊电影评论数据库

数据集链接：[http://snap.stanford.edu/data/web-Movies.html](http://snap.stanford.edu/data/web-Movies.html)

该数据集总计有评论7,911,684条，涉及用户889,176名，产品253,059个，时间跨度为1997年8月-2012年10月。该数据集的每一条评论将生成一条模拟订单，清洗无效数据后总计导入了约760万条模拟订单，其中有顾客约88万名。

对照表

| 模拟数据 | 数据集数据 |
| :------- | :--------- |
| 顾客ID   | 用户ID     |
| 顾客名字 | 用户名     |
| 产品ID   | 商品ID     |
| 交易时间 | 评论时间   |

#### 对亚马逊网站的网络爬虫

通过亚马逊购物网站的爬虫对上文涉及的253,059个产品进行爬取，累计爬取到约25万条有效产品信息。

| 模拟数据 | 数据集数据       |
| :------- | :--------------- |
| 产品ID   | 商品ID           |
| 产品类别 | 商品类别的第一项 |
| 售价     | 商品价格         |

#### 基于算法生成的模拟数据

另外一些数据由人工智能或者常见随机分布的算法生成。

| 模拟数据 | 生成算法               |
| :------- | :--------------------- |
| 顾客性别 | 由用户名进行AI判别生成 |
| 成本     | 由售价正态分布生成     |
| 订单数量 | 由泊松分布生成         |



### 三、表的设计及建表：

- #### 表的设计

  我们的思考：

  - 满足范式？数据量大Join效率较低
  - 建立宽表
  - 实际应用场景中的结构化日志

  ***在表的设计上并没有满足范式，因为结合应用场景分析，数据仓库适合建立宽表并有一定的数据冗余***

**商品(product)表设计**

| 产品ID/p_id | 产品名/p_name | 产品类别/p_category | 成本/cost | 售价/price |
| ----------- | ------------- | ------------------- | --------- | :--------: |
| string      | string        | string              | int       |    int     |
|             |               |                     |           |            |
|             |               |                     |           |            |

- [x] product表建立：

```sql
create table product(
    p_id string,
    p_name string,
    p_category string,
    cost int,
    price int)
    row format delimited fields terminated by '\t' 
    STORED AS TEXTFILE;
```



**交易(purchase)表设计**


| 交易ID/o_id | 交易时间/o_time    | 交易数量/num | 顾客ID/c_id | 顾客名字/c_name | 顾客性别/c_name | 产品ID/p_id | 产品名/p_name | 产品类别/p_category | 成本/cost | 售价/price |
| ----------- | ------------------ | ------------ | ----------- | --------------- | --------------- | ----------- | ------------- | ------------------- | --------- | ---------- |
| int         | date(‘yyyy-MM-dd’) | int          | string      | int             | int             | string      | string        | string              | int       | int        |
|             |                    |              |             |                 |                 |             |               |                     |           |            |
|             |                    |              |             |                 |                 |             |               |                     |           |            |
- [x] purchase表建立：

```sql
create table purchase(
    o_id int,
    o_time date,
    o_num int,
    c_id string,
    c_name string,
    c_sex int,
    p_id string,
    p_name string,
    p_category string,
    cost int,
    price int)
    row format delimited fields terminated by '\t' 
    STORED AS TEXTFILE;
```



### 四、系统分析SQL查询设计：

#### 查询功能点设计：

- 获取所有产品列表（分页查询）
- 查询某产品对应订单（前端提供日期筛选）
- 获取某类别的所有产品
- 对于产品ID精确、产品名称模糊搜索
- 某产品的历史销量排行榜
- 按年月做销售金额、利润统计
- 某类别产品的销量第一
- 最受男性/女性青睐产品
- 连续三天及以上有购买记录的用户（按年份）

#### Hive-SQL语句设计

1、获取所有产品列表

```sql
select p_id,p_name,p_category,cost,price
from product
where ref_id between #{low} and #{high}
```

2、查询某产品对应订单（前端提供日期筛选）

```sql
select o_id,o_time,o_num,c_id,c_name,c_sex
from purchase
where p_id= #{p_id}
```

3、获取某类别的所有产品 

```sql
select p_id,p_name,p_category,cost,price
from (select row_number() over() as ref_id,p_id,p_name,p_category,cost,price 
      from product
      where p_category=#{category}) pro 
where ref_id between #{low} and #{high}
```

4、对于产品ID精确、产品名称模糊搜索

```sql
select p_id,p_name,p_category,cost,price
from product
where p_id=#{s} or p_name like concat_ws("%",#{s},"%")
```

5、某产品的历史销量排行榜

```sql
select sum(o_num) s_vol,p_id,p_name,p_category,cost,price
from purchase
group by p_id,p_name,p_category,cost,price
order by s_vol
desc limit #{num}
```

6、按月做销售金额总计：

```sql
select sum(o_num*price) total,year(o_time) year,month(o_time) month
from purchase
group by year(o_time),month(o_time)
```

7、按月做销售利润统计：

```sql
select sum(o_num*price-o_num*cost) profit,year(o_time) year, month(o_time) month
from purchase
group by year(o_time),month(o_time)
```

8、每个类别第一：

```sql
select p_category,p_id,p_name,cost,price,s_vol
        from(select p_category,p_id,p_name,cost,price,s_vol,
                rank() over(partition by p_category order by s_vol desc) r
             from(  select p_category,p_id,p_name,cost,price,sum(o_num) s_vol
                    from purchase
                    where p_category is not null
                    group by p_category,p_id,p_name,cost,price) t1) t2
        where t2.r=1 and s_vol >1
```

9、男生、女生最喜欢的商品前三：

```sql
select c_sex,s_vol,p_id,p_name,p_category,cost,price
from(select c_sex,s_vol,p_id,p_name,p_category,cost,price,
	rank() over(partition by c_sex order by s_vol desc) rank
from(select c_sex,sum(o_num) s_vol,p_id,p_name,p_category,cost,price
        from purchase
        group by c_sex,p_id,p_name,p_category,cost,price) t1) t2
        where t2.rank > 0 and t2.rank &lt; 4
```

10、连续三个月购买过商品的用户列表：

```sql
select c_id,c_name,c_sex,concat_ws('|', collect_set(t4.data_dt)) data_dt
from (
select
    c_id,c_name,c_sex,
    data_dt
from
    (select
    c_id,c_name,c_sex,
    data_dt,
    datediff(data_dt,lag2) lag2_diff,
    datediff(data_dt,lag1) lag1_diff,
    datediff(data_dt,lead1) lead1_diff,
    datediff(data_dt,lead2) lead2_diff
from
    (select
    c_id,c_name,c_sex,
    data_dt,
    lag(data_dt,2,'1970-01') over(partition by c_id order by data_dt) lag2,
    lag(data_dt,1,'1970-01') over(partition by c_id order by data_dt) lag1,
    lead(data_dt,1,'1970-01') over(partition by c_id order by data_dt) lead1,
    lead(data_dt,2,'1970-01') over(partition by c_id order by data_dt) lead2
from
    (select c_id,c_name,c_sex,o_time data_dt
from pur
    where year(o_time) = #{year}
    )t1)t2)t3
where
    (lag2_diff=2 and lag1_diff=1)
    or
    (lag1_diff=1 and lead1_diff=-1)
    or
    (lead1_diff=-1 and lead2_diff=-2) )t4
    group by c_id,c_name,c_sex
```

### 五、前后端框架搭建

- 前端采用Vue+ElementUI构建查询分析页面
- 后端采用SpringMVC+Spring+Mybatis构建，便于使用JDBC连接hiveserver2



### 六、页面展示

<center><img src=".\document_img\img_3.png" alt="img_2" style="zoom: 60%;" /></center>

​                                                                      图 所有产品列表页面

<center><img src=".\document_img\img_4.png" alt="img_4" style="zoom: 60%;" /></center>

​                                                                       图 销售金额统计

<center><img src=".\document_img\img_5.png" alt="img_5" style="zoom: 60%;" /></center>

​                                                                 图 通过ID/名称搜索产品

<center><img src=".\document_img\img_6.png" alt="img_6" style="zoom: 60%;" /></center>

​                                                                        图 商品详情页
