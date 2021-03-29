#### nginx 相关笔记
##### 监听端口
> ##### 监听 82 端口
      listen  82;                                                            
      server_name localhost;                                                                                                                                                               
      charset utf-8;      
      # 匹配路径                                                                                                                                                                                            
      location ^~ /invite/ {                                                                                                                                                                      
       root  /opt/production/webappmanage/h5/;
       index index.html index.htm;                                                                                                                                                             
       error_page 405 =200 $uri;
      }           
          
    # 全路径匹配                                                                                                                                                                                        
       location /sioo-tenant-web/{
          proxy_pass http://${host}:{port}/;                                                                                                                                                                                       
      }
#####  nginx  设置跨域

    if ($request_method = 'OPTIONS') {
        add_header 'Access-Control-Allow-Origin' '*';
        add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
        add_header 'Access-Control-Allow-Headers' '*';
        
        return 204;
     }
  
    if ($request_method = 'GET') {
        add_header 'Access-Control-Allow-Origin' '*';
        add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
        add_header 'Access-Control-Allow-Headers' '*';
    }
      
    if ($request_method = 'POST') {
        add_header 'Access-Control-Allow-Origin' '*';
        add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
        add_header 'Access-Control-Allow-Headers' '*';
    }   
    
#####      