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
