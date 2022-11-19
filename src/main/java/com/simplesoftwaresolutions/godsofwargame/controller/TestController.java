package com.simplesoftwaresolutions.godsofwargame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("")
    public String display(){
        return "index";
    }
}
