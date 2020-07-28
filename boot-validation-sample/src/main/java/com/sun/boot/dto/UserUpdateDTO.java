package com.sun.boot.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-27 18:54
 */
@Data
public class UserUpdateDTO {

    @NotNull(message = "{UserUpdateDTO.id.NotNull}")
    private Integer id;

}
