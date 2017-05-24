package com.whoami.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Whoami on 2017/5/19.
 */
@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient client;
    @RequestMapping("/hello")
    public String hello() {
        ServiceInstance localInstance = client.getLocalServiceInstance();
        return "Hello World: "+ localInstance.getServiceId()+":"+localInstance.getHost()+":"+localInstance.getPort();
    }
}
