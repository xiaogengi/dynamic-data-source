### 该项目为动态数据源切换

​	1）首先下载项目

​			git clone https://github.com/xiaogengi/dynamic-data-source.git

​	2）项目中首先需要配置自己的数据源

​			（1）在 resource 下有 application.properties 或者 application.yml 配置文件，根据自己项目实际情况选择

![image-20200706154537625](/Users/didi/Library/Application Support/typora-user-images/image-20200706154537625.png)

​			（2）此处使用 application.yml 举例，one，two 分别为两个数据源，这里需要修改为自己的数据源

```yml
spring:
  datasource:
    one:
      username: root
      password: root.
      jdbc-url: jdbc:mysql://localhost:3306/one?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC
      driver-class-name: com.mysql.cj.jdbc.Driver

    two:
      username: root
      password: root.
      jdbc-url: jdbc:mysql://localhost:3306/two?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC
      driver-class-name: com.mysql.cj.jdbc.Driver
```

​			（3）在 com.xg.dynamic.data.source.enums 下找到 DynamicDataSourceEnum 枚举类，将枚举类中的 内容改为自己的数据源名称![image-20200706162737266](/Users/didi/Library/Application Support/typora-user-images/image-20200706162737266.png)

​		（4）在 com.xg.dynamic.data.source.annotation 下找到 DS 注解，将默认值修改为 自己需要的默认的数据源（加注解，不指定数据源的情况下触发）

![image-20200706162849809](/Users/didi/Library/Application Support/typora-user-images/image-20200706162849809.png)

​		（5）在 com.xg.dynamic.data.source.config 下找到 DynamicDataSourceAutoConfig 类，修改数据源对应的bean名称，以及读取配置的数据源名称。

​			@Qualitier("twoDataSource") 是 根据beanName 注入bean 到方法入参中

![image-20200706163401408](/Users/didi/Library/Application Support/typora-user-images/image-20200706163401408.png)

3）在 com.xg.dynamic.data.source.test 包下找到 DynamicDataSourceTest 类，将枚举切换为自己定义好的数据源进行测试![image-20200706163818687](/Users/didi/Library/Application Support/typora-user-images/image-20200706163818687.png)

4）在 test 下找到 com.xg.dynamic.data.source 包下的 DynamicDataSourceApplicationTests 类，然后调用自己定义的方法进行测试![image-20200706163655091](/Users/didi/Library/Application Support/typora-user-images/image-20200706163655091.png)

5）打印结果为 不加注解 不会进行切面更换数据源，加了注解 但是没有指定 dataSourceName 使用的是默认的数据源

![image-20200706163907707](/Users/didi/Library/Application Support/typora-user-images/image-20200706163907707.png)

