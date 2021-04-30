package com.chinaliyq.consumerserver.controller;

import com.chinaliyq.consumerserver.beans.Person;
import com.chinaliyq.consumerserver.interfaces.IMyApi;
import com.chinaliyq.consumerserver.service.HealthStatusService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author: liyq
 * @Description: SpringCloud_TWO
 * @Date: 2021/4/28 - 23:16
 * @Version: 1.0
 **/
@RestController
public class MainController {
    @Value("${server.port}")
    String port;

    @Autowired
    DiscoveryClient discoveryClient;

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    //手动获取实例对象，自行拼装远程访问路径
    @RequestMapping("/getHi-1")
    public Object getHi(){
        //经过服务的appname名称来寻找
        List<ServiceInstance> instances = discoveryClient.getInstances("providerserver");
        System.out.println(instances);
        if (instances.size() > 0){
            ServiceInstance instance = instances.get(0);
            System.out.println("对..象："+instance);
            System.out.println(instance.getHost());
            System.out.println(instance.getServiceId());
            String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";
            System.out.println(url);
            RestTemplate restTemplate = new RestTemplate();
            String object = restTemplate.getForObject(url, String.class);
            return "hi...1,端口号：：" + port + ",返回值：" + object;
        }
        return "hi...1,端口号：" + port;
    }
    //服务的hostname不能修改，不然无法经过服务名称识别；只能经过ip:端口/具体的服务来访问
    @RequestMapping("/getHi-2")
    public Object getHi1(){
        //经过application.name查找
        List<InstanceInfo> instancesByVipAddress = eurekaClient.getInstancesByVipAddress("providerserver",false);
        System.out.println(instancesByVipAddress);
        if (instancesByVipAddress.size() > 0){
            Random random = new Random();
            int i = random.nextInt(instancesByVipAddress.size());
            InstanceInfo instance = instancesByVipAddress.get(i);
            System.out.println(instance);
            System.out.println(instance.getHostName());
            String url = "http://" + instance.getHostName() + ":" + instance.getPort() + "/getHi";
            System.out.println(url);
            RestTemplate restTemplate = new RestTemplate();
            String object = restTemplate.getForObject(url, String.class);
            return "hi...2,端口号：：" + port + ",返回值：" + object;
        }
        return "hi...2,端口号：" + port;
    }
    @RequestMapping("/getHi-3")
    public Object getHi11(){
        //经过application.name查找
        ServiceInstance instance = loadBalancerClient.choose("providerserver");
        System.out.println(instance);
        if (null != instance){
            String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";
            System.out.println(url);
//            RestTemplate restTemplate = new RestTemplate();
            String object = restTemplate.getForObject(url, String.class);
            System.out.println(object);
            return "3,端口号：：" + port + ",返回值：" + object;
        }
        return "3,端口号：" + port;
    }

    @RequestMapping("/getRest1")
    public Object getRest1(){
        String url = "http://providerserver/getHi";
        String object = restTemplate.getForObject(url, String.class);
        return "hi...4,端口号：：" + port + ",返回值：" + object;
    }

    @RequestMapping("/getMap")
    public Object getMap(){
        String url = "http://providerserver/getMap";
//        Map map = restTemplate.getForObject(url, Map.class);
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(url, Map.class);
        System.out.println(forEntity);
        System.out.println("Map:" + forEntity.getBody());
        return forEntity.getBody();
    }
    @RequestMapping("/getPerson")
    public Object getPerson(){
//        String url = "http://providerserver/getPerson1";
//        Person map = restTemplate.getForObject(url, Person.class);
        String url = "http://providerserver/getPerson2?name={0}";
        ResponseEntity<Person> forEntity = restTemplate.getForEntity(url, Person.class, "中国");
        System.out.println(forEntity);
        System.out.println("M-a-p:" + forEntity.getBody());
        return forEntity.getBody();
    }

    @RequestMapping("/postPerson")
    public Object postPerson(String name,char sex,int age){
        System.out.println(name+","+sex +","+age);
        Person person = new Person(name, sex, age);
        System.out.println(person);
        String url = "http://providerserver/postPerson";
        final ResponseEntity<Person> forEntity = restTemplate.postForEntity(url, person, Person.class);
        System.out.println(forEntity);
        return forEntity.getBody();
    }

    @GetMapping("/getToBaidu")
    public Object getToBaidu(String name,char sex,int age, HttpServletResponse response) throws Exception {
        System.out.println("端口：" + port);
        Person person = new Person(name, sex, age);
        System.out.println(person);
        String url = "http://providerserver/getToBaidu";
        URI uri = restTemplate.postForLocation(url, person, Person.class);
        System.out.println("uri:" + uri);
        System.out.println(String.valueOf(uri));
        System.out.println(uri.toString());
        if (null!=uri)
        response.sendRedirect(uri.toString());
        return uri;
    }


    @Autowired
    HealthStatusService healthStatusService;

    @GetMapping("/health")
    public String health(Boolean status){
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus().toString();
    }

}
