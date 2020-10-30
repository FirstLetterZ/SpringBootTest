package com.zpf.demo.user.been;

public class UserRegisterInfo {
    private String account = null;
    private String password = null;

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

    public boolean checkLegal() {
        return account != null && account.length() > 0 && password != null && password.length() > 0;
    }
}
