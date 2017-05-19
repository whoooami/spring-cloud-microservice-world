package com.whomai.feign;

import com.netflix.discovery.DiscoveryManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
public class ProductApplication {
//    private static final Logger LOG = LoggerFactory.getLogger(ProductApplication.class);

    public static void main(String[] args){
        SpringApplication.run(ProductApplication.class,args);
//        LOG.info("Register ShutdownHook");
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
//                LOG.info("Shutting down product service, unregister from Eureka!");
                DiscoveryManager.getInstance().shutdownComponent();
            }
        });
    }
}