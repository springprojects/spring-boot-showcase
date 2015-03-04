package com.gaodashang.jmd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * comments.
 *
 * @author eva
 */
@Controller
public class MainController {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping(value = "/helloWorld")
    @ResponseBody
    public String helloWorld() {

        return helloWorldService.helloWorld();
    }
}
