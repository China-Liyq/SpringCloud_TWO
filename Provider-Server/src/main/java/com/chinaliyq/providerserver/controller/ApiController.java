package com.chinaliyq.providerserver.controller;

import com.chinaliyq.providerserver.beans.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liyq
 * @Description: 废弃，待加原因
 * @Date: 2021/4/30 - 9:56
 * @Version: 1.0
 **/
@RestController
public class ApiController {
    @Value("${server.port}")
    String port;

    @GetMapping("/alive")
    public String getAlive(){
        return "getApi----,端口：" + port;
    }

    @RequestMapping("/getMapPerson")
    public Map getPerson1(){
        HashMap<String, String> map = new HashMap<>();
        Person person = new Person("liuq", '男', 20);
        System.out.println("端口：" + port);
        System.out.println(999);
        map.put("person",person.toString());
        System.out.println(map);
        return map;
    }
}
