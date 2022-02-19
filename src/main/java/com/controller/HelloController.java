package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
public class HelloController {
//
//    @Value("${min}")
//    private BigDecimal min;

    @ResponseBody
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "1";
    }
}

