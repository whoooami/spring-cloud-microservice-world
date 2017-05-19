package com.whomai.feign.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.whomai.feign.service.RemoteRecommendService;
import com.whomai.feign.vo.Product;
import com.whomai.feign.vo.Recommendation;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class ProductController {

//    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    RemoteRecommendService remoteRecommendService;

    @RequestMapping("/product/recommends")
    @HystrixCommand(fallbackMethod = "callRecommendFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
    })
    public List<Recommendation> remoteRecommends(@RequestParam(value = "productId",  required = true) int productId){
        return remoteRecommendService.getRecommendations(productId);
    }

    public List<Recommendation> callRecommendFallback(int productId) {
        return Collections.emptyList();
    }

    @RequestMapping("/product/{productId}")
    public Product getProduct(@PathVariable int productId) {

        int pt = RandomUtils.nextInt(1000);
//        LOG.info("/product called, processing time: {}", pt);

//        sleep(pt);

//        LOG.debug("/product return the found product");
        return new Product(productId, "name", 123);
    }
}