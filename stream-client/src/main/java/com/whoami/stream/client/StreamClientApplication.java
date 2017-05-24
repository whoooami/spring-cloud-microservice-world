package com.whoami.stream.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Whoami on 2017/5/23.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class StreamClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamClientApplication.class, args);
    }
}
