package com.pan.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"/","/index"})
    public String hello(){
        return "index";
    }
    @RequestMapping("/adminindex")
    public String helloAdmin(){
        return "adminindex";
    }
    @RequestMapping("/hello")
    public String helloq(){
        return "adminpub";
    }
}
