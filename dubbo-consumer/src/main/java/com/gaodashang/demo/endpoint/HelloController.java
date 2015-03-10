package com.gaodashang.demo.endpoint;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaodashang.demo.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * comments.
 *
 * @author eva
 */
@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello(String data) {
        String result = helloService.hello(data);
        return result;
    }
}
