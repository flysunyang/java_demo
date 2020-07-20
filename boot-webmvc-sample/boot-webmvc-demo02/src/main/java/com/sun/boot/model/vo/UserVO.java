package com.sun.boot.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 14:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserVO implements Serializable {
    
    private Integer id;

    private String username;
    
}
