package com.zpf.demo.user.been;

import com.zpf.demo.user.entity.UserBaseEntity;
import com.zpf.demo.user.entity.UserExpendEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserPersonalInfo {
    public String usrId = null;
    public String account = null;
    public String nickname = null;
    public int accountState = 0;
    public String sexStr = null;
    public int sexType = 0;
    public int age = 0;
    public String email = null;
    public String phone = null;
    public String sdf = null;//个性签名
    public String remark = null;//备注
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date lastStateTime;

    public void setBaseInfo(UserBaseEntity info) {
        usrId = info.getUsrId();
        account = info.getAccount();
        nickname = info.getNickname();
        remark = info.getRemark();
        accountState = info.getAccountState();
        createTime = info.getCreateTime();
        lastStateTime = info.getLastStateTime();
    }

    public void setExpandInfo(UserExpendEntity info) {
        sexType = info.getSex();
        if (sexType == 1) {
            sexStr = "男";
        } else if (sexType == 2) {
            sexStr = "女";
        } else {
            sexStr = "保密";
        }
        age = info.getAge();
        email = info.getEmail();
        phone = info.getPhone();
        sdf = info.getSdf();
    }
}
