package com.sun.boot.bean.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-20 17:12
 */
@Data
@Accessors(chain = true)
public class UserDO {

    private Integer id;

    private String username;

}
