package com.mainiway.controller;

import com.mainiway.annotation.AuthAccess;
import com.mainiway.common.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController extends BaseController {
    //private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/test1")
    @AuthAccess
    public Object testLogin() {
        return "test1";
    }

    @GetMapping("/test2")
    public String test() {
        return "test2";
    }
}