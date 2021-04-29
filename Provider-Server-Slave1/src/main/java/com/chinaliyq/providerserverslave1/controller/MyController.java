package com.chinaliyq.providerserverslave1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
