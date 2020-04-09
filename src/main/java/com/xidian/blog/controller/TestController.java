package com.xidian.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 米
 * @date 2020/3/21
 */
@Controller

public class TestController {
    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public String hello (HttpServletRequest request,@RequestParam(value = "description",required = false, defaultValue = "springboot-thymeleaf")String description){

        return "blog/default/header";
    }
}
