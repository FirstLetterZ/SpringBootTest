package com.example.test.controller;

import com.example.test.been.TestBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by ZPF on 2019/6/4.
 */
@RestController
class FirstController {
    private final TestBean testBean;

    @Autowired
    public FirstController(TestBean testBean) {
        this.testBean = testBean;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello World!" + testBean.toString();
    }

}
