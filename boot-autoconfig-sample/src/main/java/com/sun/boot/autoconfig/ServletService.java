package com.sun.boot.autoconfig;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-16 10:52
 */
public class ServletService {

    private String msg;

    public ServletService(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
