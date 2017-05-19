package com.whoami.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringCloudApplication
@EnableTurbine
@EnableHystrixDashboard
public class TurbineApplication {
    public static void main(String[] args){
        SpringApplication.run(TurbineApplication.class,args);
    }
}