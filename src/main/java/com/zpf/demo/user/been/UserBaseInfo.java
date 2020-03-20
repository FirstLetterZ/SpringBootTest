package com.zpf.demo.user.been;

import java.io.Serializable;

/**
 * Created by ZPF on 2019/6/5.
 */
public class UserBaseInfo implements Serializable {

    private String usrId = null;

    private String account = null;

    private String password = null;

    private String nickname = null;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
