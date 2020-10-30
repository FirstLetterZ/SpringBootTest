package com.zpf.demo.global;

public enum AccountState {
    WAIT_INIT(0,"账号尚未初始化"),//等待初始化
    NORMAL(1,""),//正常使用
    AUTHENTICATION(3,"账号认证中"),//认证中
    AUTH_FAIL(2,"账号认证失败"),//认证失败
    FREEZING(4,"账号被冻结");//冻结

    private int code;
    private String desc;
    private AccountState(int code,String desc) {
        this.code = code;
        this.desc=desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
