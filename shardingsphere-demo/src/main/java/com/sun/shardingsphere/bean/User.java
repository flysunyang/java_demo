package com.sun.shardingsphere.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-02 16:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user") // 这里表名使用shardingsphere设置的名称(虚拟)
public class User implements Serializable {

    private Long id;

    private String username;

    private String password;

    private Integer roleId;
}
