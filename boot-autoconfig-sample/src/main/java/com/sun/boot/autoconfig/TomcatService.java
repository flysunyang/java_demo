package com.sun.boot.autoconfig;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-16 10:51
 */
public class TomcatService {

    private String msg;

    public TomcatService(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
