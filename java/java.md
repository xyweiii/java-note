#### java相关笔记

------
#####  java 手动配置事务
```java
@Autowired
private DataSource dataSource;

@Bean
public DataSourceTransactionManager dataSourceTransactionManager() {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource);
    return transactionManager;
}

@Bean
public TransactionTemplate transactionTemplate(DataSourceTransactionManager dataSourceTransactionManager) {
    TransactionTemplate transactionTemplate = new TransactionTemplate(dataSourceTransactionManager);
    transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
    return transactionTemplate;
}

// 代码中使用 transactionTemplate事务

public boolean result() {
    return template.execute(action -> {
        try {
            int result = userMapper.insert(new User());
            if (result == 1) {
                action.setRollbackOnly();
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            action.setRollbackOnly();
        }
        return false;
    });
}

```
------
##### maven 引用本地jar包
```xml
 <dependency>
            <groupId>e-iceblue</groupId>
            <artifactId>spire.doc</artifactId>
            <version>4.3.2</version>
            <scope>system</scope>
            <systemPath>${pom.basedir}/src/main/resources/spire-doc/spire.doc-4.3.2.jar</systemPath>
        </dependency>
```
------

##### maven skip test

> -DskipTests，不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下。 
>  -Dmaven.test.skip=true，不执行测试用例，也不编译测试用例类。

##### maven 下载源码
> mvn dependency:sources

##### maven 编译源码
> 
    <build>
      <plugins>
          <plugin>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-maven-plugin</artifactId>
              <configuration>
                  <includeSystemScope>true</includeSystemScope>
              </configuration>
          </plugin>
    
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-source-plugin</artifactId>
              <configuration>
                  <attach>true</attach>
              </configuration>
              <executions>
                  <execution>
                      <phase>compile</phase>
                      <goals>
                          <goal>jar</goal>
                      </goals>
                  </execution>
              </executions>
          </plugin>
      </plugins>
    </build>