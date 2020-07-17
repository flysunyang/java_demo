package com.sun.boot.bo;

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
public class UserDetailBO implements Serializable {

    private static final long serialVersionUID = -995230901142291583L;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String pwd;

}
