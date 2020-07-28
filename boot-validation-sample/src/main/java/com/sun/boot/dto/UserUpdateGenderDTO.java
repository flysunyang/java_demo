package com.sun.boot.dto;

import com.sun.boot.validator.InEnum;
import com.sun.boot.web.constants.GenderEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-27 18:05
 */
@Data
public class UserUpdateGenderDTO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer id;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    @InEnum(value = GenderEnum.class, message = "性别必须是 {value}")
    private Integer gender;

}
