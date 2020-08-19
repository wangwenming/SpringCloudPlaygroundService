package com.quanfangtongvip.springcloudplayground.controller;

import com.quanfangtongvip.springcloudplayground.context.enums.ErrorCode;
import com.quanfangtongvip.springcloudplayground.context.exception.BaseException;
import com.quanfangtongvip.springcloudplayground.context.response.Result;
import com.quanfangtongvip.springcloudplayground.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/")
    public Result get(@RequestParam(value="name") String name) {
        return helloService.sayHello(name);
    }
}
