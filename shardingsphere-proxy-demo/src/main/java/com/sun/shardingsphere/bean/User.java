package com.sun.shardingsphere.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-03 16:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")
public class User {

    private Long id;

    private String username;

    private String password;

    private Integer roleId;

}
