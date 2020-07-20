package com.sun.boot.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-20 16:22
 */
@Data
@Accessors(chain = true)
public class UserVO implements Serializable {

    private Integer id;

    private String username;

}
