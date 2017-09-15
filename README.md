### MicroserviceWorld
###### Spring cloud microservice example.
###### tutorial http://samchu.logdown.com/posts/313414-microservice-with-springcloud
include module:
* discovery
* gateway
* config
* reservation-service
* reservation-client
* hystrix-dashboard
* monitor
* zipkin
* auth-server

### How to run it?

1. MacOS/Unix/Linux (/etc/hosts), windows (C:\Windows\System32\drivers\etc)  
    127.0.0.1 config discovery zipkin
2. install rabbitmq use brew.  
    brew install rabbitmq.
3. run it in order.
    discovery -> EurekaServiceApplication.java [Show me](http://localhost:8761/)  
    config -> ConfigServiceApplication.java  
    gateway -> ApiGatewayApplication.java  
    auth-server -> AuthApplication.java   
    reservation-service -> ReservationServiceApplication.java  
    reservation-client -> ReservationClientApplication.java  
    hystrix-dashboard -> HystrixDashboardApplication.java [Show me](http://localhost:8010/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8889%2Fturbine.stream)  
    monitor -> TurbineApplication.java [Show me](http://localhost:8889/turbine.stream)  
    zipkin -> ZipkinApplication.java [Show me](http://localhost:9411/)
4. You can run multi reservation-service and reservation-client test loadbalance with [click this](http://localhost:10000/reservation/hello)   
    or apache bench [ab -c100 -n 10000 http://localhost:10000/reservation/hello]   
    or Jmeter test it.  
5. How to run OAuth2 in auth-server.  
    * In the browser：  
    [http://localhost:10000/uaa/oauth/authorize?response_type=code&client_id=whoami&scope=app&state=123456&redirect_uri=http://www.google.com](http://localhost:10000/uaa/oauth/authorize?response_type=code&client_id=whoami&scope=app&state=123456&redirect_uri=http://www.google.com), first time input username:whoami and password:123456.  
    * In the terminal. Get last code from last address and replace {{code}} in next command.  
    curl -X POST -H "Cache-Control: no-cache" -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code={{code}}&redirect_uri=http://www.google.com' "http://whoami:123456@localhost:10000/uaa/oauth/token"  
    
    

![hystrix_monitor](https://raw.githubusercontent.com/whoooami/spring-cloud-microservice-world/master/images/localhost_8010_hystrix_monitor.png)


### Reference
1. [Getting started with Spring Cloud by Josh Long](https://www.youtube.com/watch?v=SFDYdslOvu8)
2. This lab is references [YouTube Getting Started with Spring Cloud](https://www.youtube.com/watch?v=cCEvFDhe3os), Thank you Dr. Dave Syer, Josh Long.[]
3. [Dive into Eureka – nobodyiam's blog](http://nobodyiam.com/2016/06/25/dive-into-eureka/)
4. [About Spring cloud security OAuth2](http://blog.spring-cloud.io/blog/oauth-authorize.html)
