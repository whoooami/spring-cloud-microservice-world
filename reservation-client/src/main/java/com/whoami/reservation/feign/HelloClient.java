package com.whoami.reservation.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Whoami on 2017/5/19.
 */
@FeignClient("reservation-service")
public interface HelloClient {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();
}
