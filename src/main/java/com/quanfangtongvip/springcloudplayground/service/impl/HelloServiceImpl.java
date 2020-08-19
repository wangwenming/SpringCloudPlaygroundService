package com.quanfangtongvip.springcloudplayground.service.impl;

import com.quanfangtongvip.springcloudplayground.context.enums.ErrorCode;
import com.quanfangtongvip.springcloudplayground.context.exception.BaseException;
import com.quanfangtongvip.springcloudplayground.context.response.Result;
import com.quanfangtongvip.springcloudplayground.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    public Result sayHello(String name) {
        // ① Result 报错
        if (null == name) {
            return Result.failure(ErrorCode._10008);
        }
        // ② 抛出自定义异常
        int minLength = 2;
        if (name.length() < minLength) {
            throw new BaseException(ErrorCode._10004.getErrorCode(), "姓名至少" + minLength + "个字符");
        }
        // ③ Error，可通过捕获 Throwable
        log.info("名字第三个字符是：{}", name.charAt(2));

        return Result.success("Hello, " + name);
    }
}
