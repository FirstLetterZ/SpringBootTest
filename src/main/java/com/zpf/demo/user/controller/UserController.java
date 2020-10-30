package com.zpf.demo.user.controller;

import com.zpf.demo.global.ErrorCode;
import com.zpf.demo.global.ResponseBean;
import com.zpf.demo.user.been.UserPersonalInfo;
import com.zpf.demo.user.been.UserRegisterInfo;
import com.zpf.demo.user.entity.UserBaseEntity;
import com.zpf.demo.user.entity.UserExpendEntity;
import com.zpf.demo.user.service.UserBaseService;
import com.zpf.demo.user.service.UserExpendService;
import com.zpf.demo.user.util.TokenManager;
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
    public ResponseBean<Boolean> registerAccount(@RequestBody UserRegisterInfo info) {
        ResponseBean<Boolean> result = new ResponseBean<>();
        if (info == null || !info.checkLegal()) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        try {
            UserBaseEntity baseEntity = userBaseService.createNewUser(info);
            UserExpendEntity userExpendEntity = new UserExpendEntity();
            userExpendEntity.setUsrId(baseEntity.getUsrId());
            userExpandService.createNewUser(userExpendEntity);
            result.data = true;
        } catch (Exception e) {
            e.printStackTrace();
            result.data = false;
        }
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBean<UserPersonalInfo> signIn(@RequestBody UserRegisterInfo info) {
        ResponseBean<UserPersonalInfo> result = new ResponseBean<>();
        if (info == null || !info.checkLegal()) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        UserBaseEntity baseEntity;
        try {
            baseEntity = userBaseService.findUserBaseInfoByAccount(info.getAccount());
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrInfo(ErrorCode.QUERY_FAIL);
            return result;
        }
        UserExpendEntity expendEntity = userExpandService.findUserExpandInfo(baseEntity.getUsrId());
        UserPersonalInfo personalInfo = new UserPersonalInfo();
        personalInfo.setBaseInfo(baseEntity);
        personalInfo.setExpandInfo(expendEntity);
        personalInfo.token = TokenManager.createToken(baseEntity.getUsrId());
        result.data = personalInfo;
        return result;
    }


//    @RequestMapping(value = "/delAccount", method = RequestMethod.POST)
//    public boolean delAccount(@RequestBody UserRegisterInfo info) {
//        if (info != null) {
//            try {
//                userBaseService.deleteUser(info);
//                return true;
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//        return false;
//    }
}
