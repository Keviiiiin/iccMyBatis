# mybatis的入门
## mybatis的环境搭建
    
* 第一步：创建maven工程并导入坐标

* 第二步：创建实体类和dao的接口

* 第三步：创建Mybatis的主配置文件SqlMapConifg.xml

* 第四步：创建映射配置文件IUserDao.xml

## 环境搭建的注意事项：
* 1.在Mybatis中,持久层的操作接口名称和映射文件也叫做：Mapper。所以：IUserDao 和 IUserMapper是一样的

* 2.mybatis的映射配置文件位置必须和dao接口的包结构相同

* 3.映射配置文件的mapper标签namespace属性的取值必须是dao接口的全限定类名

* 4.映射配置文件的操作配置（select），id属性的取值必须是dao接口的方法名
    
#### 当我们遵从了2.3.4之后，我们在开发中就无须再写dao的实现类，MyBatis将为我们创建DAO的实现类，我们只需要编写接口

## 	mybatis的入门案例
    
* 第一步：读取配置文件

    * 编译后，标注为Sources Root和Resources Root的目录下的所有配置文件将被放置到IDE build出来的对应结构的目录下，比如Web项目的WEB-INF/classes,Maven项目的target/classes,普通项目的out/production
    
    * 因此，src目录和resources目录下的文件，或是与该类同处一个目录，直接写文件名，如"pro.properties"，否则加上文件夹名，如"dao/pro.properties"。另外，以下方式1)要加"/",方式2)不用
    
    * 获取方式(getResourceAsStream同理)：   
        
        1)类名.class.getResource 
            
        2)类名.class.getClassLoader.getResource
    
    * <a href="https://cnblogs.com/robbinluobo/p/7931812.html">参考文章</a>
        
* 第二步：创建SqlSessionFactory工厂

    * 构建者模式
    
* 第三步：创建SqlSession

    * 调用的是工厂的openSession方法

* 第四步：创建Dao接口的代理对象

    * getMapper()，其实就是Proxy.newProxyInstance()
    
    * <a href="https://blog.csdn.net/qq_40645822/article/details/101844675">参考文章</a>

* 第五步：执行dao中的方法

    * 其实就是InvocationHandler接口的实现类调用invoke方法

* 第六步：释放资源

## 入门案例的分析
<img src="img/入门案例的分析.png">

## mybatis基于注解的入门案例：

* 只需要在Dao接口对应的方法上加注解、写sql语句，然后写测试运行方法即可。

* 把IUserDao.xml(Mapper)移除，在dao接口的方法上使用@Select注解，并且指定SQL语句。_注解和配置文件不能同时存在_

* 同时,需要在SqlMapConfig.xml中的mapper配置时，使用class属性（而不是resource)指定dao接口的全限定类名。

_明确：
    我们在实际开发中，都是越简便越好，所以都是采用不写dao实现类的方式。
    不管使用XML还是注解配置。
    但是Mybatis它是支持写dao实现类的。_
   
## 自定义Mybatis的分析：
### mybatis在使用代理dao的方式实现增删改查时做什么事呢？
    只有两件事：
        第一：通过getMapper创建代理对象
        第二：在代理对象中调用selectList方法
   		
### 自定义mybatis能通过入门案例看到类
   		class Resources
   		class SqlSessionFactoryBuilder
   		interface SqlSessionFactory
   		interface SqlSession
## #{}与${}的区别
* #{}表示一个占位符号
    
    * 通过#{}可以实现 preparedStatement 向占位符中设置值，自动进行 java 类型和 jdbc 类型转换，#{}可以有效防止 sql 注入。 #{}可以接收简单类型值或 pojo 属性值。
    
    * 如果 parameterType 传输单个简单类型值， #{}括号中可以是 任意名称。----------------------[注1]
    
    * #{}将传入的数据都当成一个字符串，也就是给数据加一个""

* ${}表示拼接 sql 串
    
    * 通过${}可以将 parameterType 传入的内容直接拼接在 sql 中且不进行 jdbc 类型转换， ${}可以接收简
    单类型值或java对象属性值
    
    * 如果 parameterType 传输单个简单类型值， ${}括号中只能是 value。

* 总结：#{}不会导致sql注入，而${}适用于order by 语句（不会产生多余的双引号）

## OGNL表达式：
   	
   	Object Graphic Navigation Language
   	
   	对象	图	导航	   语言
   	
* 它是通过对象的取值方法来获取数据。在写法上把get给省略了。

* 比如：我们获取用户的名称

    * 类中的写法：user.getUsername();

    * OGNL表达式写法：user.username

* mybatis中为什么能直接写username,而不用user.呢：

* 因为在parameterType中已经提供了属性所属的类，所以此时不需要写对象名

## parameterType采用包装类对象QueryVo

* 当查询条件是*综合的条件*时，可以用包装对象QueryVo把条件封装起来

* 实现需求：根据用户名模糊查询用户信息，其中查询条件放到QueryVo的User属性中

_注意_

* mysql数据库在windows系统中不区分大小写，所以keyColumn属性以及sql语句中（#{}里传的参数除外）的大小写是被忽略的，当然，不要忘记[注1] :)

## 当实体类的属性名和数据库列名不匹配时

### 解决方法一：别名查询

```sql
select id as userId,username as userName,address as userAddress,sex as userSex,birthday as userBirthday
from user
```
* 在sql语句的层面解决了问题，效率较高

### 解决方法二：修改配置文件

```xml
<!-- 配置查询结果的列名和实体类的属性名的对应关系 -->
<resultMap id="suibianxie" type="com.iccKevin.domain.User">
    <!-- 主键字段的对应 -->
    <id property="userId" column="id"></id>
    <!--非主键字段的对应-->
    <result property="userName" column="username"></result>
    <result property="userAddress" column="address"></result>
    <result property="userSex" column="sex"></result>
    <result property="userBirthday" column="birthday"></result>
</resultMap>

<!--同时把resultType改成resultMap-->

<!-- 查询所有 -->
<select id="findAll" resultMap="suibianxie">
    select * from user;
</select>
```

* 增加了解析xml的时间，但不用改sql语句，提高了开发效率

## mybatis中的连接池

* 配置的位置：

    * 主配置文件SqlMapConfig.xml中的dataSource标签，type属性就是表示采用何种连接池方式。

* type属性的取值：

    * POOLED：采用传统的javax.sql.DataSource规范中的连接池，mybatis中有针对规范的实现

    * UNPOOLED：采用传统的获取连接的方式，虽然也实现Javax.sql.DataSource接口，但是并没有使用池的思想。

    * JNDI：采用*服务器*提供的JNDI技术实现，来获取DataSource对象，不同的服务器所能拿到的DataSource不一样。

        * 注意：如果不是web或者maven的war工程，是不能使用的。

        * tomcat服务器采用的连接池是dbcp连接池。

* 如果*空闲池*还有连接的话直接拿一个出来用,如果*空闲池*没有可用的连接，会查看*活动池*中的连接是否已经达到*最大数量*。如果*活动池*中的连接*没有*达到最大数量, 会创建一个新的连接。如果*活动池*中的连接已经达到最大数量, 会判断活动池中哪个是*最先进来的(Oldest)*, 然后对该链接进行处理并返回
      
* <a href="https://www.cnblogs.com/mkl7/p/10745912.html">参考文章</a> 

## mybatis中的事务
   
* 通过sqlsession对象的commit方法和rollback方法实现事务的提交和回滚

## 动态sql

* 通过<if> <where> <foreach>标签实现

## 多表查询

### 对于一个多对一的关系，例如：用户->订单，如果拿出某一个订单，他只能属于一个用户。所以Mybatis就把多对一看成了一对一。

### 需求:查询所有账户信息，关联查询下单用户信息。（多对一）

* 方式一
    
    * 定义一个Account类的子类AccountUser，并定义sql查询结果集所有的字段，这样sql语句查询的结果就被封装到了AccountUser类中
    
* 方式二
    
    * 使用 resultMap，定义专门的 resultMap 用于映射查询结果。
    
    * 我们可以在 Account 类中加入一个 User 类的对象来代表这个账户是哪个用户的。

### 一对多、多对多

* 例子：查询用户，并得到其对应的账户信息（一个用户可能有多个）。查询用户，并得到其对应的角色信息（一个用户可能有多个，需要借助中间表）

* sql语句采用左外连接，配置文件加入resultMap，User类中加入相应属性。

* 实际这两个差不多:)只不过*多对多*有中间表，sql语句有些差别

### Mybatis中的延迟加载

* 问题：在查询用户时，用户下的账户信息应该是什么时候用什么时候查。而在查询账户时，账户的所属用户信息应该是随着账户查询时一起查询出来。
    
* 什么是延迟加载
    * 先查询主表信息，若用到从表数据，再去查询从表信息，用不到，就不查询。按需加载（懒加载）
* 什么是立即加载
    * 不管用不用，只要一调用方法，马上发起查询。
    	
_一对多，多对多：通常情况下采用延迟加载。_

_多对一，一对一：通常情况下采用立即加载。_

* 步骤：

    * 主配置文件设置settings标签开启延迟加载；
    
    * 多表查询的resultMap标签设置select属性和column属性，告知使用的方法名和参数类型；
    
    * 实现Account类的findByUid功能。
    
## 缓存

* 什么是缓存

    * 存在于内存中的临时数据。

* 为什么使用缓存

    * 减少和数据库的交互次数，提高执行效率。但有可能出现缓存和数据库中数据不同步的问题。

* 适用于缓存：

    * 经常查询并且不经常改变的数据。

    * 数据的正确与否对最终结果影响不大的。
### Mybatis中的一级缓存和二级缓存
    
#### 一级缓存：指的是Mybatis中SqlSession对象的缓存。
    
当我们执行查询之后，查询的结果会同时存入到SqlSession为我们提供一块区域中。
该区域的结构是一个Map。当我们再次查询同样的数据，mybatis会先去sqlsession中查询是否有，有的话直接拿出来用。
当SqlSession对象消失时，mybatis的一级缓存也就消失了。

_一级缓存是 SqlSession 范围的缓存，当调用 SqlSession 的修改，添加，删除， commit()， close()等方法时，就会清空一级缓存_
   		
#### 二级缓存:指的是Mybatis中SqlSessionFactory对象的缓存。

_由同一个SqlSessionFactory对象创建的SqlSession共享其缓存。_

* 二级缓存的使用步骤：

    第一步：让Mybatis框架支持二级缓存（在SqlMapConfig.xml中配置），默认cacheEnabled就是true

    第二步：让当前的映射文件支持二级缓存（在IUserDao.xml中配置）

    第三步：让当前的操作支持二级缓存（在select标签中配置）
    
* 注意：SqlSessionFactory中存放的只是数据，SqlSession调用时不用发起新的查询，但要重新封装对象。

## 复杂关系映射的注解

#### @Results 注解
    代替的是标签<resultMap>
    该注解中可以使用单个@Result 注解，也可以使用@Result 集合
    @Results（{@Result()， @Result()}）或@Results（@Result()）
#### @Result 注解
代替了 <id>标签和<result>标签
@Result 中 属性介绍：
id 是否是主键字段
column 数据库的列名
property 需要装配的属性名
one 需要使用的@One 注解（@Result（one=@One）（）））
many 需要使用的@Many 注解（@Result（many=@many）（）））
#### @One 注解（一对一）
代替了<assocation>标签，是多表查询的关键，在注解中用来指定子查询返回单一对象。
@One 注解属性介绍：
select 指定用来多表查询的 sqlmapper
fetchType 会覆盖全局的配置参数 lazyLoadingEnabled。。
使用格式：
@Result(column=" ",property="",one=@One(select=""))
#### @Many 注解（多对一）
代替了<Collection>标签,是是多表查询的关键，在注解中用来指定子查询返回对象集合。
注意：聚集元素用来处理“一对多”的关系。需要指定映射的 Java 实体类的属性，属性的 javaType
（一般为 ArrayList）但是注解中可以不定义；
使用格式：
@Result(property="",column="",many=@Many(select=""))