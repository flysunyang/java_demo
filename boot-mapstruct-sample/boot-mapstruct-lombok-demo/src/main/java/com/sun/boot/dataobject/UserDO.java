package com.sun.boot.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 11:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO implements Serializable {

    private static final long serialVersionUID = 7039184693775729324L;

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
