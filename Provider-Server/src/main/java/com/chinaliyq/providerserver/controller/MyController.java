package com.chinaliyq.providerserver.controller;

import com.chinaliyq.providerserver.beans.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
        map.put("Map端口:",port);
        return map;
    }

    @GetMapping("/getPerson1")
    public Person getPerson1(){
        Person person = new Person("liuq", '男', 20);
        System.out.println("端口：" + port);
        return person;
    }

    @GetMapping("/getPerson2")
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


}
