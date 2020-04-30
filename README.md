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

* 第二步：创建SqlSessionFactory工厂

* 第三步：创建SqlSession

* 第四步：创建Dao接口的代理对象

* 第五步：执行dao中的方法

* 第六步：释放资源

## 入门案例的分析
<img src="img/入门案例的分析.png">

## mybatis基于注解的入门案例：

* 把IUserDao.xml(Mapper)移除，在dao接口的方法上使用@Select注解，并且指定SQL语句
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
