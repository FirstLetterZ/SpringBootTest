package com.zpf.demo.user.been;

import com.zpf.demo.user.entity.UserBaseEntity;
import com.zpf.demo.user.entity.UserExpendEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserPersonalInfo {
    private String usrId = null;
    private String account = null;
    private String nickname = null;
    private String realName = null;//
    private String sexStr = null;
    private int sexType = 0;
    private int age = 0;
    private String email = null;
    private String phone = null;
    private String sdf = null;//个性签名
    private int accountState = 0;
    private String remark = null;//备注
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activeTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date freezingTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date failureTime;

    public void setBaseInfo(UserBaseEntity info) {
        usrId = info.getUsrId();
        account = info.getAccount();
        nickname = info.getNickname();
        remark = info.getRemark();
        sexType = info.getSex();
        if (sexType == 1) {
            sexStr = "男";
        } else if (sexType == 2) {
            sexStr = "女";
        } else {
            sexStr = "保密";
        }
        age = info.getAge();
        accountState = info.getAccountState();
        createTime = info.getCreateTime();
        activeTime = info.getActiveTime();
        freezingTime = info.getFreezingTime();
        failureTime = info.getFailureTime();
    }

    public void setExpandInfo(UserExpendEntity info) {
        email = info.getEmail();
        phone=info.getPhone();
        sdf=info.getSdf();
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    public int getSexType() {
        return sexType;
    }

    public void setSexType(int sexType) {
        this.sexType = sexType;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAccountState() {
        return accountState;
    }

    public void setAccountState(int accountState) {
        this.accountState = accountState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getFreezingTime() {
        return freezingTime;
    }

    public void setFreezingTime(Date freezingTime) {
        this.freezingTime = freezingTime;
    }

    public Date getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSdf() {
        return sdf;
    }

    public void setSdf(String sdf) {
        this.sdf = sdf;
    }
}
