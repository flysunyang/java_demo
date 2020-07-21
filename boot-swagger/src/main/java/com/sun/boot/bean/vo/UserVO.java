package com.sun.boot.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-21 15:46
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "用户 VO")
public class UserVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    private Integer id;

    private String username;

}
