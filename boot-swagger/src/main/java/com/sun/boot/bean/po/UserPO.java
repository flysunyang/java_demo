package com.sun.boot.bean.po;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-21 15:46
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "用户 PO")
public class UserPO {

    private Integer id;

    private String username;

}
