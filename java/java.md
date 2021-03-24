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

```
------



