package com.sun.shardingsphere.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-03 11:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_dict")
public class Dictionary {

    private Long id;

    private String name;

    private String code;

}
