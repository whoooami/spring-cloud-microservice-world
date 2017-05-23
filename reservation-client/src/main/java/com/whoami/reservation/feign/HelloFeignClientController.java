package com.whoami.reservation.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Whoami on 2017/5/19.
 */
@RestController
public class HelloFeignClientController {

    @Autowired
    private HelloClient helloClient;

    public String helloFallBack() {
        return "hello fall back.";
    }

    @HystrixCommand(fallbackMethod = "helloFallBack")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        System.out.println("invoke remote hello api.");
        return helloClient.hello();
    }

}