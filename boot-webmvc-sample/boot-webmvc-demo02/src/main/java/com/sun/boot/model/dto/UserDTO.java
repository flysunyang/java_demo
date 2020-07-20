package com.sun.boot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 14:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Integer id;

    private String username;

}
