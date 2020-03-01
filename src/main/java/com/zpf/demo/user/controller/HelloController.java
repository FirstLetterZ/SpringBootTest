package com.zpf.demo.user.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello World!-tes";
    }
}
