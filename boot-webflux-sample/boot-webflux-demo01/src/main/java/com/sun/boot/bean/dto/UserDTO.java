package com.sun.boot.bean.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-20 16:34
 */
@Data
public class UserDTO implements Serializable {

    private Integer id;

    private String username;

}
