package com.zpf.demo.global;

public class ResponseBean<T> {
    public int code = 200;
    public T data;
    public String msg;

    public ResponseBean() {
    }

    public ResponseBean(T data) {
        this.data = data;
    }

    public ResponseBean(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public void setErrInfo(ErrorCode code) {
        this.code = code.getCode();
        this.msg = code.getDesc();
    }
}
