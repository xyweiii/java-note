# java 相关笔记

![jetbrains-variant-4](/Users/xywei/Downloads/jetbrains-logos/jetbrains-variant-4.png)






<img src="https://camo.githubusercontent.com/5fa4dc7ff49e4a518c047200657e42382acaf0a612325e9fb560cd909d4b8902/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f496e74656c6c694a253230494445412d2545362538462539302545342542452539422545362539342541462545362538432538312d626c75652e737667" alt="使用IntelliJ IDEA开发维护" data-canonical-src="https://img.shields.io/badge/IntelliJ%20IDEA-%E6%8F%90%E4%BE%9B%E6%94%AF%E6%8C%81-blue.svg" style="max-width:100%;">


//数据库事务配置


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





https://blog.csdn.net/qq_33404395/article/details/83377382

https://dylanxu.iteye.com/blog/1403038

https://blog.csdn.net/a83370892/article/details/66476623


正则匹配


 https://www.cnblogs.com/fozero/p/7868687.html
 http://tool.oschina.net/uploads/apidocs/jquery/regexp.html
 
 



 
 
 select *  from user_control uc where length(uc.user_kind)>2 and id in (select us.user_id from user_statistics us);

  @Override
    public void afterPropertiesSet() throws Exception {
        Connection connection = factory.createConnection();
        channel = connection.createChannel(false);
        channel.queueBind("gateway.cmpp.deliver", "sms.callback", "gateway.cmpp.deliver");
    }
 
 
@RabbitHandler
    public void process(JSONObject msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        try {
            
             channel.basicAck(deliveryTag, true);
           
           
            }
        } catch (Exception e) {
            xxxx;
        }
    }



System.out.println(JSON.parse(JSON.toJSON(str).toString()));

JSONObject msg = JSON.parseObject(str);


#mongo读写分离


replicaSet=sioo&readPreference=secondaryPreferred


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MockTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void TestAAA() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/mongo/test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Mock test"));
    }
}


linux同步 系统/网络时间
ntpdate cn.pool.ntp.org



//cmpp模拟网关
http://www.simpleteam.com/doku.php?do=export_xhtml&id=message:cmpp_simulator




netstat -anp|grep 9217








ps -ef|grep cpu|grep -v grep|awk '{print $2}'



1、ps -ef 显示所有的进程，其中后面的e是显示结果的意思，f是显示完整格式，其他比如-w是不限制列宽显示，具体可见ps --help all

2、ps -ef|grep cpu作用是把包括cpu这个关键字的进程都显示出来

3、如2.1所示，ps -ef|grep cpu会把grep cpu的进程也统计进来，因此用ps -ef|grep cpu|grep -v grep去除grep进程

4、最后，只包含cpu关键字的进程筛选结果作为输入给awk '{print $2}'，这个部分的作用是提取输入的第二列，而第二列正是进程的PID


//mongo通配符索引
db.example.createIndex({ "attributes.$**": 1 });
mongo  升级 下载4.2.1 包



db.adminCommand( { getParameter: 1, featureCompatibilityVersion: 1 } )

db.adminCommand( { setFeatureCompatibilityVersion: "4.2" } )


http://downloads.mongodb.org/linux/mongodb-linux-x86_64-rhel70-4.2.1.tgz

https://docs.mongodb.com/manual/release-notes/4.2-upgrade-standalone/


https://blog.csdn.net/u____/article/details/79859503



//mongodb索引

db.collection.createIndex(
  { "$**" : 1 },
  { "wildcardProjection" :
    { "fieldA" : 0, "fieldB.fieldC" : 0 }
  }
)


// maven 删除依赖

<dependencies>
    <dependency>
      <groupId>org.apache.maven</groupId>
      <artifactId>maven-embedder</artifactId>
      <version>2.0</version>
      <exclusions>
        <exclusion>
          <groupId>org.apache.maven</groupId>
          <artifactId>maven-core</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
    ...
  </dependencies>
    

    
    <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-mongodb</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>*</groupId>
                    <artifactId>*</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

  
  
配置jmc元成功访问

 -Dcom.sun.management.jmxremote.port=7001
 -Dcom.sun.management.jmxremote 
 -Dcom.sun.management.jmxremote.authenticate=false 
 -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=your ip



    @PostConstruct
    public void createDelayQueue() {
        //新建exchange
        Exchange exchange = ExchangeBuilder.directExchange(MsgSendQueue.EXCHANGE_NAME).durable(true).build();
        rabbitAdmin.declareExchange(exchange);
        //新建queue
        Queue queue = QueueBuilder.durable(MsgSendQueue.SEND_PACKAGE_DELAY.getQueueName()).withArgument("x-dead-letter-exchange", MsgSendQueue.EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key", MsgSendQueue.SEND_PACKAGE_NORMAL.getQueueName())
                .withArgument("x-message-ttl", 60000)
                .build();
        rabbitAdmin.declareQueue(queue);
        //绑定
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(MsgSendQueue.SEND_PACKAGE_DELAY.getQueueName()).noargs());
    }

    
    
expireAfterAccess: 当缓存项在指定的时间段内没有被读或写就会被回收。

expireAfterWrite：当缓存项在指定的时间段内没有更新就会被回收。

refreshAfterWrite：当缓存项上一次更新操作之后的多久会被刷新。

//java开启远程调试

java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
       -jar target/myapplication-0.0.1-SNAPSHOT.jar
       
  
  
java -jar -Xms3584m -Xmx3584m -Xss512k -XX:MaxDirectMemorySize=192M /webapps/app.jar


java 8 

为什么 Lambda 表达式(匿名类) 不能访问非 final 的局部变量呢？ 因为实例变量存在堆中，而局部变量是在栈上分配，Lambda 表达(匿名类) 会在另一个线程中执行。
如果在线程中要直接访问一个局部变量，可能线程执行时该局部变量已经被销毁了，
而 final 类型的局部变量在 Lambda 表达式(匿名类) 中其实是局部变量的一个拷贝。



// 并发编程网  好多有用的东西
http://ifeve.com/



        
        
        
package examples;

public class ExampleBean {

    // Fields omitted

    @ConstructorProperties({"years", "ultimateAnswer"})
    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }
}



//nohup 不生成nohup.out 

nohup java -jar /xxx/xxx/xxx.jar    >/dev/null 2>&1   &
https://www.cnblogs.com/wangsongbai/p/10215155.html




//springboot 日志设置

logging:
  file: /data/logs/sioo-deliver/sioo-deliver.log
  file.max-history: 2
  file.max-size: 100MB
  level:
    root: info
    com:
      sioo: info
      
      
       maven -B -f /var/lib/jenkins/workspace/tenant-web-dev/pom.xml -U -Dmaven.test.skip=true -Dmaven.javadoc.skip=true install


//home
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}


https://segmentfault.com/a/1190000004315890



//mybatis  不使用驼峰

**property  name="useActualColumnNames" value="true"


//thymleaf   replace fragment   insert 
https://blog.csdn.net/wangmx1993328/article/details/84747497




**处理异常信息**
https://blog.csdn.net/jaryle/article/details/90378637


**mysql连接时区问题**
jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false

jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false

**spring cloud hystrix**
https://www.cnblogs.com/duan2/p/9307217.html


**linux  mount disk **

//列出 所有设备
lsblk


//显示所有的磁盘信息
fdisk -l


//格式化 磁盘
mkfs -t ext4 -c /dev/sdb


//格式化 磁盘
mkfs.ext4　　/dev/sdb



dmsetup status
dmsetup remove_all



echo "/dev/sdb /data ext4 defaults 0 0">>/etc/fstab

--
远程debugger
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
       -jar target/myapplication-0.0.1-SNAPSHOT.jar

//maven   下载source 文档

 mvn dependency:sources
//修复 ntfs, i是符
chkdsk  i:   /f  


#### maven 不同仓库 之间切换 ， 删除   _remote.repositories 修复jar 包引用不了问题

### mac/Linux   查询满足条件的文件   并删除 find . -name "_remote.repositories" | xargs rm -rf


### mongo聚合,数据量大 开启 diskUse

{allowDiskUse: true}


### Redis 模糊匹配 一次删除多个key


### redis-cli -h host -p port keys "pattern" |xargs redis-cli -h host -p port del


