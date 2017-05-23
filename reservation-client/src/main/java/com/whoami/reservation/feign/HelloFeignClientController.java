package com.whoami.reservation.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Whoami on 2017/5/19.
 */
@RestController
public class HelloFeignClientController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HelloClient helloClient;

    public String helloFallBack() {
        logger.debug("hello fall back.");
        return "hello fall back.";
    }

    @HystrixCommand(fallbackMethod = "helloFallBack")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        logger.debug("invoke remote hello api.");
        return helloClient.hello();
    }

}