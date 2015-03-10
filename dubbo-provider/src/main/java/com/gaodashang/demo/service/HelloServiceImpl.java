package com.gaodashang.demo.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * comments.
 *
 * @author eva
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String data) {
        return "Hello, " + data + ".";
    }
}
