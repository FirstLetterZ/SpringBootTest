package com.zpf.demo.user.controller;

import com.zpf.demo.user.been.UserPersonalInfo;
import com.zpf.demo.user.been.UserRegisterInfo;
import com.zpf.demo.user.entity.UserBaseEntity;
import com.zpf.demo.user.entity.UserExpendEntity;
import com.zpf.demo.user.service.UserBaseService;
import com.zpf.demo.user.service.UserExpendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZPF on 2019/6/4.
 */
@RestController
class UserController {
    @Autowired
    private UserBaseService userBaseService;
    @Autowired
    private UserExpendService userExpandService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello World!";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean registerAccount(@RequestBody UserRegisterInfo info) {
        if (info != null) {
            try {
                UserBaseEntity baseEntity = userBaseService.createNewUser(info);
                UserExpendEntity userExpendEntity = new UserExpendEntity();
                userExpendEntity.setUsrId(baseEntity.getUsrId());
                userExpandService.createNewUser(userExpendEntity);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserPersonalInfo signIn(@RequestBody UserRegisterInfo info) {
        if (info != null) {
            try {
                UserBaseEntity baseEntity = userBaseService.findUserBaseInfoByAccount(info.getAccount());
                if (baseEntity == null) {
                    return null;
                }
                UserExpendEntity expendEntity = userExpandService.findUserExpandInfo(baseEntity.getUsrId());
                UserPersonalInfo personalInfo = new UserPersonalInfo();
                personalInfo.setBaseInfo(baseEntity);
                personalInfo.setExpandInfo(expendEntity);
                return personalInfo;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }


    @RequestMapping(value = "/delAccount", method = RequestMethod.POST)
    public boolean delAccount(@RequestBody UserRegisterInfo info) {
        if (info != null) {
            try {
//                userBaseService.deleteUser(info);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
