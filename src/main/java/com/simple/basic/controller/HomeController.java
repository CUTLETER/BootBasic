package com.simple.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "hello";
    }

//    Rest API
//    @GetMapping("/")
//    public @ResponseBody String home() { 이러면 화면에 바로 hello 텍스트가 찍힘
//        return "hello";
//    }

}
