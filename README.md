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













#IDEA CODE


56ZS5PQ1RF-eyJsaWNlbnNlSWQiOiI1NlpTNVBRMVJGIiwibGljZW5zZWVOYW1lIjoi5q2j54mI5o6I5p2DIC4iLCJhc3NpZ25lZU5hbWUiOiIiLCJhc3NpZ25lZUVtYWlsIjoiIiwibGljZW5zZVJlc3RyaWN0aW9uIjoiRm9yIGVkdWNhdGlvbmFsIHVzZSBvbmx5IiwiY2hlY2tDb25jdXJyZW50VXNlIjpmYWxzZSwicHJvZHVjdHMiOlt7ImNvZGUiOiJJSSIsInBhaWRVcFRvIjoiMjAyMC0wMy0xMCJ9LHsiY29kZSI6IkFDIiwicGFpZFVwVG8iOiIyMDIwLTAzLTEwIn0seyJjb2RlIjoiRFBOIiwicGFpZFVwVG8iOiIyMDIwLTAzLTEwIn0seyJjb2RlIjoiUFMiLCJwYWlkVXBUbyI6IjIwMjAtMDMtMTAifSx7ImNvZGUiOiJHTyIsInBhaWRVcFRvIjoiMjAyMC0wMy0xMCJ9LHsiY29kZSI6IkRNIiwicGFpZFVwVG8iOiIyMDIwLTAzLTEwIn0seyJjb2RlIjoiQ0wiLCJwYWlkVXBUbyI6IjIwMjAtMDMtMTAifSx7ImNvZGUiOiJSUzAiLCJwYWlkVXBUbyI6IjIwMjAtMDMtMTAifSx7ImNvZGUiOiJSQyIsInBhaWRVcFRvIjoiMjAyMC0wMy0xMCJ9LHsiY29kZSI6IlJEIiwicGFpZFVwVG8iOiIyMDIwLTAzLTEwIn0seyJjb2RlIjoiUEMiLCJwYWlkVXBUbyI6IjIwMjAtMDMtMTAifSx7ImNvZGUiOiJSTSIsInBhaWRVcFRvIjoiMjAyMC0wMy0xMCJ9LHsiY29kZSI6IldTIiwicGFpZFVwVG8iOiIyMDIwLTAzLTEwIn0seyJjb2RlIjoiREIiLCJwYWlkVXBUbyI6IjIwMjAtMDMtMTAifSx7ImNvZGUiOiJEQyIsInBhaWRVcFRvIjoiMjAyMC0wMy0xMCJ9LHsiY29kZSI6IlJTVSIsInBhaWRVcFRvIjoiMjAyMC0wMy0xMCJ9XSwiaGFzaCI6IjEyMjkxNDk4LzAiLCJncmFjZVBlcmlvZERheXMiOjAsImF1dG9Qcm9sb25nYXRlZCI6ZmFsc2UsImlzQXV0b1Byb2xvbmdhdGVkIjpmYWxzZX0=-SYSsDcgL1WJmHnsiGaHUWbaZLPIe2oI3QiIneDtaIbh/SZOqu63G7RGudSjf3ssPb1zxroMti/bK9II1ugHz/nTjw31Uah7D0HqeaCO7Zc0q9BeHysiWmBZ+8bABs5vr25GgIa5pO7CJhL7RitXQbWpAajrMBAeZ2En3wCgNwT6D6hNmiMlhXsWgwkw2OKnyHZ2dl8yEL+oV5SW14t7bdjYGKQrYjSd4+2zc4FnaX88yLnGNO9B3U6G+BuM37pxS5MjHrkHqMTK8W3I66mIj6IB6dYXD5nvKKO1OZREBAr6LV0BqRYSbuJKFhZ8nd6YDG20GvW6leimv0rHVBFmA0w==-MIIElTCCAn2gAwIBAgIBCTANBgkqhkiG9w0BAQsFADAYMRYwFAYDVQQDDA1KZXRQcm9maWxlIENBMB4XDTE4MTEwMTEyMjk0NloXDTIwMTEwMjEyMjk0NlowaDELMAkGA1UEBhMCQ1oxDjAMBgNVBAgMBU51c2xlMQ8wDQYDVQQHDAZQcmFndWUxGTAXBgNVBAoMEEpldEJyYWlucyBzLnIuby4xHTAbBgNVBAMMFHByb2QzeS1mcm9tLTIwMTgxMTAxMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxcQkq+zdxlR2mmRYBPzGbUNdMN6OaXiXzxIWtMEkrJMO/5oUfQJbLLuMSMK0QHFmaI37WShyxZcfRCidwXjot4zmNBKnlyHodDij/78TmVqFl8nOeD5+07B8VEaIu7c3E1N+e1doC6wht4I4+IEmtsPAdoaj5WCQVQbrI8KeT8M9VcBIWX7fD0fhexfg3ZRt0xqwMcXGNp3DdJHiO0rCdU+Itv7EmtnSVq9jBG1usMSFvMowR25mju2JcPFp1+I4ZI+FqgR8gyG8oiNDyNEoAbsR3lOpI7grUYSvkB/xVy/VoklPCK2h0f0GJxFjnye8NT1PAywoyl7RmiAVRE/EKwIDAQABo4GZMIGWMAkGA1UdEwQCMAAwHQYDVR0OBBYEFGEpG9oZGcfLMGNBkY7SgHiMGgTcMEgGA1UdIwRBMD+AFKOetkhnQhI2Qb1t4Lm0oFKLl/GzoRykGjAYMRYwFAYDVQQDDA1KZXRQcm9maWxlIENBggkA0myxg7KDeeEwEwYDVR0lBAwwCgYIKwYBBQUHAwEwCwYDVR0PBAQDAgWgMA0GCSqGSIb3DQEBCwUAA4ICAQAF8uc+YJOHHwOFcPzmbjcxNDuGoOUIP+2h1R75Lecswb7ru2LWWSUMtXVKQzChLNPn/72W0k+oI056tgiwuG7M49LXp4zQVlQnFmWU1wwGvVhq5R63Rpjx1zjGUhcXgayu7+9zMUW596Lbomsg8qVve6euqsrFicYkIIuUu4zYPndJwfe0YkS5nY72SHnNdbPhEnN8wcB2Kz+OIG0lih3yz5EqFhld03bGp222ZQCIghCTVL6QBNadGsiN/lWLl4JdR3lJkZzlpFdiHijoVRdWeSWqM4y0t23c92HXKrgppoSV18XMxrWVdoSM3nuMHwxGhFyde05OdDtLpCv+jlWf5REAHHA201pAU6bJSZINyHDUTB+Beo28rRXSwSh3OUIvYwKNVeoBY+KwOJ7WnuTCUq1meE6GkKc4D/cXmgpOyW/1SmBz3XjVIi/zprZ0zf3qH5mkphtg6ksjKgKjmx1cXfZAAX6wcDBNaCL+Ortep1Dh8xDUbqbBVNBL4jbiL3i3xsfNiyJgaZ5sX7i8tmStEpLbPwvHcByuf59qJhV/bZOl8KqJBETCDJcY6O2aqhTUy+9x93ThKs1GKrRPePrWPluud7ttlgtRveit/pcBrnQcXOl1rHq7ByB8CFAxNotRUYL9IF5n3wJOgkPojMy6jetQA5Ogc8Sm7RG6vg1yow==







select *  from user_control uc where length(uc.user_kind)>2 and id in (select us.user_id from user_statistics us);




hmyx001
ygph
test1
zhusy1
jisukeji1
test01
qt001
dyht01
hqh12
luotest
shqd001
lianyi01
pw001
shuiyou
huyue01
TDCSZY
sankeshu
jennydongnew
boyuan01
1116548
shxs001
gerald
lengnuan
lixing01
wxsmsh001
ygtest
clienta2
clienta4
clienta5
clienta6
clienta7
clienta8
clienta9





 6422898731645952 shxs001     http 通知
 6103150198258688 boyuan01    http 验证码  通知
 5543804268182528 huyue01     http 验证码
 5450255430319104 shuiyou     http 验证码  通知
 5128119026711552 lianyi01    http 通知
 4994276747639808 shqd001     http 通知
 4790069415640064 dyht01      http 通知



2b458af0d5864e1994785ba562d992ed



