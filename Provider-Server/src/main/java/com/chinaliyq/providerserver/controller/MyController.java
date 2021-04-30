package com.chinaliyq.providerserver.controller;

import com.chinaliyq.providerserver.beans.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: liyq
 * @Description: SpringCloud_TWO
 * @Date: 2021/4/28 - 23:14
 * @Version: 1.0
 **/
@RestController
public class MyController {
    @Value("${server.port}")
    String port;

    @RequestMapping("/getHi")
    public Object getHi(){
        return "hi...123,端口：" + port;
    }

    @RequestMapping("/getMap")
    public Map getMap(){
        HashMap<String, String> map = new HashMap<>();
        System.out.println(999);
        map.put("Map端口:",port);
        return map;
    }

    @RequestMapping("/getPerson1")
    public Person getPerson1(){
        Person person = new Person("liuq", '男', 20);
        System.out.println("端口：" + port);
        System.out.println(999);
        return person;
    }

    @RequestMapping("/getPerson2")
    public Person getPerson2(String name){
        Person person = new Person("liuq", '男', 20);
        person.setName(name);
        System.out.println("端口：" + port);
        return person;
    }

    @PostMapping("/postPerson")
    public Person postPerson(@RequestBody Person person){
        System.out.println("端口：" + port);
        System.out.println(person);
        return person;
    }

    @PostMapping("/getToBaidu")
    public URI getToBaidu(@RequestBody Person person, HttpServletResponse response) throws Exception {
        System.out.println("端口：" + port);
        System.out.println(person);
        String url = "https://www.baidu.com/s?wd=";
        URI uri = new URI(url + person.getName().trim());
        System.out.println("123:"+uri);
        /*需要设计请求头，不然无法传值*/
        response.addHeader("Location",uri.toString());
        return uri;
    }

}
