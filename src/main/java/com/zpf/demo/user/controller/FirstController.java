package com.zpf.demo.user.controller;

import com.zpf.demo.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netscape.javascript.JSObject;

/**
 * Created by ZPF on 2019/6/4.
 */
@RestController
class FirstController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String update(@RequestBody JSObject bean) {
        return "Hello World!";
    }

}
