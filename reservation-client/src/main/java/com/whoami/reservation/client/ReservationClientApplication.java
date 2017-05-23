package com.whoami.reservation.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.whoami.reservation.feign.HelloClient;
import com.whoami.reservation.feign.HelloFeignClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrix
@SpringBootApplication
//@EnableFeignClients(basePackageClasses = HelloClient.class)
//@ComponentScan(basePackageClasses = HelloClient.class)
@EnableFeignClients(basePackages = "com.whoami.reservation")
@ComponentScan(basePackages = "com.whoami.reservation")
public class ReservationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationClientApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }
}

@RestController
@RequestMapping("/reservations")
class ReservationApiGatewayRestController{

    @Autowired
    private RestTemplate restTemplate;

    public Collection<String> getReservationNamesFallback(){
        return Collections.emptyList();
    }

    @HystrixCommand(fallbackMethod = "getReservationNamesFallback")
    @RequestMapping("/names")
    public Collection<String> getReservationNames(){

        ParameterizedTypeReference<Resources<Reservation>> ptr =
                new ParameterizedTypeReference<Resources<Reservation>>(){};

        ResponseEntity<Resources<Reservation>> responseEntity =
                this.restTemplate.exchange("http://reservation-service/reservations",
                        HttpMethod.GET, null, ptr);

        Collection<String> nameList = responseEntity
                .getBody()
                .getContent()
                .stream()
                .map(Reservation::getReservationName)
                .collect(Collectors.toList());

        return nameList;
    }
}

class Reservation{
    private Long id;

    private String reservationName;
    public Reservation(){}

    public Reservation(String reservationName) {
        this.reservationName = reservationName;
    }

    public Long getId() {
        return id;
    }

    public String getReservationName() {
        return reservationName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Reservation{");
        sb.append("id=").append(id);
        sb.append(", reservationName='").append(reservationName).append("'}");
        return sb.toString();
    }
}