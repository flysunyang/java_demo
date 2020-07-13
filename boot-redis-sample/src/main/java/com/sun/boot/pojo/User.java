package com.sun.boot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-10 14:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -5723097603353164405L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String address;

}
