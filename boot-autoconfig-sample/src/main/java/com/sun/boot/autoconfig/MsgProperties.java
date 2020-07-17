package com.sun.boot.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-16 9:51
 */
@ConfigurationProperties(prefix = "my.msg")
public class MsgProperties {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
