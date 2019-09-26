# workspace
workspace





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


#mongo读写分离


replicaSet=sioo&readPreference=secondaryPreferred

5248421718129664









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







提交数 大于详情数,batchId:5970114735504385
提交数 大于详情数,batchId:5970083390946304
提交数 大于详情数,batchId:5970051769039872
提交数 大于详情数,batchId:5970051741514752
提交数 大于详情数,batchId:5970005175041024
提交数 大于详情数,batchId:5969973717498880





(CHT0103 HTTP 提交失败) http status code not 200: 500:3569
(-5 -5) %!s(<nil>) -5:9




CHT0101:40
CHT0103:19056
-5:9
-6:32078
____________
(CHT0103 HTTP 提交失败) http status code not 200: 500:4237
(CHT0103 HTTP 提交失败) 通道不存在, channel not found, channel=5903319261510656, error=key=channel-info:5903319261510656, error=channel: unmarshal json failed, error=invalid character 'O' looking for beginning of value, json=OK:9819
(CHT0101 HTTP 提交超时) Post http://119.23.154.247:8000/SendSms.asp: net/http: request canceled (Client.Timeout exceeded while awaiting headers):40
(CHT0103 HTTP 提交失败) 通道不存在, channel not found, channel=5903319261510656, error=key=channel-info:5903319261510656, error=MOVED 8312 172.19.84.226:6379:5000
(-6 -6) %!s(<nil>) -6:32078
(-5 -5) %!s(<nil>) -5:9


















