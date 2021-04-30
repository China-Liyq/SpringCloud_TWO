package com.chinaliyq.consumerserver.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: liyq
 * @Description: 使用feign 搭配  ip : 端口
 * @Date: 2021/4/30 - 9:23
 * @Version: 1.0
 **/
//@FeignClient(name = "provider", url = "http://localhost:81")
@FeignClient(name = "providerserver")
public interface IMyApi {

    @RequestMapping("/alive")
    String alive();

    @RequestMapping("/getMap")
    Object getMap();

    @RequestMapping("/getPerson")
    Object getPerson();

    @RequestMapping("/getPerson1")
    Object getPerson1();

    @RequestMapping("/getPerson2")
    Object getPerson2(@RequestParam("name") String name);

}
