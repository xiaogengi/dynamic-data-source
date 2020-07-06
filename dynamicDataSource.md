### 该项目为动态数据源切换

​	1）首先下载项目

​			git clone https://github.com/xiaogengi/dynamic-data-source.git

​	2）项目中首先需要配置自己的数据源

​			（1）在 resource 下有 application.properties 或者 application.yml 配置文件，根据自己项目实际情况选择

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

​			（3）在 com.xg.dynamic.data.source.enums 下找到 DynamicDataSourceEnum 枚举类，将枚举类中的 内容改为自己的数据源名称

```java
public enum DynamicDataSourceEnum {

    ONE_DATA_SOURCE("one"),// key 和 值都改为自己的数据源名称
    TWO_DATA_SOURCE("two"),// key 和 值都改为自己的数据源名称
    ;


    DynamicDataSourceEnum(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    private String dataSourceName;

    public String getDataSourceName() {
        return dataSourceName;
    }
}

```



​		（4）在 com.xg.dynamic.data.source.annotation 下找到 DS 注解，将默认值修改为 自己需要的默认的数据源（加注解，不指定数据源的情况下触发）

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DS {
		// 把 DynamicDataSourceEnum.ONE_DATA_SOURCE 改成自己的默认数据源
    DynamicDataSourceEnum dataSourceName() default DynamicDataSourceEnum.ONE_DATA_SOURCE;

}

```



​		（5）在 com.xg.dynamic.data.source.config 下找到 DynamicDataSourceAutoConfig 类，修改数据源对应的bean名称，以及读取配置的数据源名称。

​			@Qualitier("twoDataSource") 是 根据beanName 注入bean 到方法入参中

```java
@Configuration
public class DynamicDataSourceAutoConfig {

    // 数据源名称
    @Bean("oneDataSource")
  	// 配置文件中的 数据源位置
    @ConfigurationProperties(value = "spring.datasource.one")
    public DataSource oneDataSource(){
        return DataSourceBuilder.create().build();
    }

    // 数据源名称
    @Bean("twoDataSource")
  	// 配置文件中的 数据源位置
    @ConfigurationProperties(value = "spring.datasource.two")
    public DataSource twoDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean															// @Qualifier 是根据 beanName 将 bean 注入到 入参
    public DynamicDataSource dataSource(@Qualifier("oneDataSource") DataSource oneDataSource, @Qualifier("twoDataSource") DataSource twoDataSource){
        HashMap<Object, Object> map = new HashMap<>();
       	// 此处的枚举 改为自己对应数据源的名称
        map.put(DynamicDataSourceEnum.ONE_DATA_SOURCE.getDataSourceName(), oneDataSource);
        map.put(DynamicDataSourceEnum.TWO_DATA_SOURCE.getDataSourceName(), twoDataSource);
        return new DynamicDataSource(oneDataSource, map);
    }

}
```

3）在 com.xg.dynamic.data.source.test 包下找到 DynamicDataSourceTest 类，将枚举切换为自己定义好的数据源进行测试

```java
	@Component
public class DynamicDataSourceTest {

  	// 不使用数据源
    public void notAnnotation(){
        System.out.println("无注解！！！");
    }

    @DS
  	// 使用 one 数据源
    public void defaultDataSource(){
        System.out.println("defaultDataSource");
    }
  
  	// 使用 one 数据源
    @DS(dataSourceName = DynamicDataSourceEnum.ONE_DATA_SOURCE)
    public void oneDataSource(){
        System.out.println("oneDataSource");
    }
		
  	// 使用 two 数据源
    @DS(dataSourceName = DynamicDataSourceEnum.TWO_DATA_SOURCE)
    public void twoDataSource(){
        System.out.println("twoDataSource");
    }

}
```

4）在 test 下找到 com.xg.dynamic.data.source 包下的 DynamicDataSourceApplicationTests 类，然后调用自己定义的方法进行测试

```java
@SpringBootTest
class DynamicDataSourceApplicationTests {

    @Autowired
    private DynamicDataSourceTest dataSourceTest;

    @Test
    void contextLoads() {


        dataSourceTest.notAnnotation();

        dataSourceTest.defaultDataSource();

        dataSourceTest.oneDataSource();

        dataSourceTest.twoDataSource();


    }

}
```

5）打印结果为 不加注解 不会进行切面更换数据源，加了注解 但是没有指定 dataSourceName 使用的是默认的数据源

```log
无注解！！！
main dataSource is > > > > one < < < <
defaultDataSource
main----------------------------------> dataSource remove <----------------------------------
main dataSource is > > > > one < < < <
oneDataSource
main----------------------------------> dataSource remove <----------------------------------
main dataSource is > > > > two < < < <
twoDataSource
main----------------------------------> dataSource remove <----------------------------------
```

