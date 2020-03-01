package com.zpf.demo.user.controller;

import com.zpf.demo.user.been.UserPersonalInfo;
import com.zpf.demo.user.service.UserDataService;
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
    private UserDataService userDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String update(@RequestBody JSObject bean) {
        return "Hello World!";
    }

    @RequestMapping(value = "/lastUserInfo", method = RequestMethod.GET)
    public UserPersonalInfo lastUserInfo() {
        return userDataService.findLastUser();
    }

    @RequestMapping(value = "/initUserInfo", method = RequestMethod.GET)
    public boolean initUserPersonalInfo() {
        UserPersonalInfo info = new UserPersonalInfo();
        info.setRealName("真名");
        info.setAge(18);
        info.setNickname("别名");
        info.setSex("男");
        try {
            userDataService.createNewUser(info);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
    public boolean addUserPersonalInfo(@RequestBody UserPersonalInfo info) {
        if (info != null) {
            try {
                userDataService.createNewUser(info);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
