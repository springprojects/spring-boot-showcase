package com.gaodashang.demo.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * comments.
 *
 * @author eva
 */
@Controller
public class TemplateController {

    @RequestMapping(value = "/indexPage")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/loginPage")
    public String login() {
        return "logincc";
    }

    @RequestMapping(value = "/messagePage")
    public String message() {
        return "message";
    }
}
