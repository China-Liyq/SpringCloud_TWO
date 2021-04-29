package com.chinaliyq.consumerserver.beans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: liyq
 * @Description: SpringCloud_TWO
 * @Date: 2021/4/29 - 14:15
 * @Version: 1.0
 **/
@Component
public class MyRestTemplate {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
//    @Bean
//    public IRule myRule(){
//        return new RoundRobinRule();
////        return new RandomRule();
////        return new RetryRule();
//    }

}
