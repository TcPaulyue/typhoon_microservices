package com.example.rain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RainApplication {

    public static void main(String[] args){
        SpringApplication.run(RainApplication.class, args);
    }
}
