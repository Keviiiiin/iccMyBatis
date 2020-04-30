# MyBatis映射文件详解

## 一、文件介绍

在MyBatis中，Mapper映射文件就是sql语句的配置文件，其会在运行时加载sql语句并映射相应参数。在映射文件中，根据不同的sql语句性质，要使用不同的标签来包裹，其中涉及到的标签如下表：

 

## 二、增删改查示例

* insert配置示例：

    ```html
    <insert id="insertUser" parameterType="cn.com.mybatis.pojo.User">
    insert into user(username,password,gender,birthday,email,province,city)
    value(#{username},#{password},#{gender},#{birthday,jdbcType=DATE},#{email},#{province},#{city})
    </insert>
    ```

* update配置示例：
    
    ```html
    <update id="updateUserName" parameterType="cn.com.mybatis.pojo.User">
    update user set username=#{username} where id=#{id}
    </update>
    ```

* delete配置示例：

    ```html
    <delete id="deleteUser" parameterType="java.lang.Integer">
    delete from user where id=#{id}
    </delete>
    ```

* select配置示例：

    ```html 
    <select id="findUserById" parameterType="int" resultType="cn.com.mybatis.pojo.User">
    select * from user where id=#{id}
    </select>
    ```

     * 其中，paramterType为输入参数类型，resultType为输出参数类型，#{}为占位符，类似于sql语句中的?，起到预编译的作用。

## 三、属性拓展
在insert、update、delete、select配置标签中，可以配置很多属性，具体如下：

```html
<select
id="selectPerson"
parameterType="int"
resultType="hashmap"
resultMap="personResultMap"
flushCache="false"
useCache="true"
timeout="10000"
fetchSize="256"
statementType="PREPARED"
resultSetType="FORWARD_ONLY">

<insert
id="insertAuthor"
parameterType="domain.blog.Author"
flushCache="true"
statementType="PREPARED"
keyProperty=""
keyColumn=""
useGeneratedKeys=""
timeout="20">

<update
id="updateAuthor"
parameterType="domain.blog.Author"
flushCache="true"
statementType="PREPARED"
timeout="20">

<delete
id="deleteAuthor"
parameterType="domain.blog.Author"
flushCache="true"
statementType="PREPARED"
timeout="20">
```

这些属性的含义如下表：

 
## 四、总结

* MyBatis程序通过sql与数据库打交道，其sql语句是写在映射配置文件中的。通过insert、update、delete、select标签来配置增删改查语句，其属性含义参见上表。

————————————————  
版权声明：本文为CSDN博主「aFa攻防实验室」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。

<a href="https://blog.csdn.net/fageweiketang/article/details/80850312">原文链接</a>

## 实现Serializable接口的作用和必要性

* 实体类一般需要实现Serializable接口，这个接口其实是个空接口，那么这个序列化操作，到底是由谁去实现了呢？其实，看一下接口的注释说明就知道，当我们让实体类实现Serializable接口时，其实是在告诉JVM此类可被序列化，可被默认的序列化机制序列化。
* 实现Serializable接口的实体类一般是需要或者可能需要被远程调用，这就是序列化便于传输的用途。