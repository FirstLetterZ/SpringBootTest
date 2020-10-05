package com.zpf.demo.global;

public class ResponseBean<T> {
    public int code = 200;
    public T data;
    public String msg;

    public void setErrInfo(ErrorCode code) {
        this.code = code.getCode();
        this.msg = code.getDesc();
    }
}
