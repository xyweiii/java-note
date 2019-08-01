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



3659154828819456
3659138651913216
3657753917127680












System.out.println(JSON.parse(JSON.toJSON(str).toString()));
















提交成功  [{_id=4404248592776192, 个贷移动通道,count=9065}, {_id=4295211238816768, 联通网贷,count=1415}, {_id=4294943960464384,移动网贷, count=2088}, {_id=4114317874297856, 移动网贷可发全网,count=7}]


提交失败 [{_id=4294943960464384,移动网贷 ,count=6}]



回执成功 [{_id=4295211238816768,联通网贷 ,count=1410}, {_id=4294943960464384,4294943960464384 ,count=2056}, {_id=4404248592776192,个贷移动通道 ,count=5363}, {_id=4114317874297856,移动网贷可发全网, count=1}]


回执失败  [{_id=4404248592776192,个贷移动通道, count=2836}, {_id=4295211238816768, 联通网贷,count=5}, {_id=4294943960464384,移动网贷, count=30}, {_id=4114317874297856, 移动网贷可发全网,count=6}]








