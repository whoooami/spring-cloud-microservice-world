package com.whomai.feign.service;

import com.whomai.feign.vo.Recommendation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("recommend")
public interface RemoteRecommendService {
    @RequestMapping(method = RequestMethod.GET,value = "/recommend")
    public List<Recommendation> getRecommendations(
            @RequestParam(value = "productId",  required = true) int productId);
}