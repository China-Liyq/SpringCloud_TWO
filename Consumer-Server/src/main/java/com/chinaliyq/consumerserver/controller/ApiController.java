package com.chinaliyq.consumerserver.controller;

import com.chinaliyq.consumerserver.interfaces.IMyApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liyq
 * @Description: SpringCloud_TWO
 * @Date: 2021/4/30 - 13:50
 * @Version: 1.0
 **/
@RestController
public class ApiController {
    @Value("${server.port}")
    String port;
    //使用单一接口API
    @Autowired
    IMyApi myApi;

    @GetMapping("/getValue")
    public String getValue(){
        printPort();
        return "正常:" + port;
    }

    @GetMapping("/alive")
    public String getApiAlive(){
        printPort();
        return myApi.alive();
    }

    @GetMapping("/getApiMap")
    public Object getName(){
        printPort();
        return "consumer端：" + port +","+ myApi.getMap();
    }

    @GetMapping("/getApiPerson1")
    public Object getApiPerson(){
        printPort();
        return "consumer端：" + port +"," + myApi.getPerson1();
    }
    @GetMapping("/getApiPerson2")
    public Object getApiPerson(String name){
        System.out.println(name);
        printPort();
        System.out.println(222);
        Object person2 = myApi.getPerson2(name);
        if (null != person2){
            System.out.println(person2);
            System.out.println(333);
            return "consumer端：" + port +"," + person2 ;
        }
        return "consumer端：" + port;
    }

    /**
     * 打印端口
     */
    private void printPort() {
        System.out.println("consumer端：" + port);
    }

}
