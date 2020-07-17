package com.sun.boot.bo;

import com.sun.boot.dataobject.AddressDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 13:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentBO implements Serializable {

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

    private AddressDO addressDO;

}
