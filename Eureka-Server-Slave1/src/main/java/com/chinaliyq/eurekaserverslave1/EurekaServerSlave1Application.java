package com.chinaliyq.eurekaserverslave1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerSlave1Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerSlave1Application.class, args);
    }

}
