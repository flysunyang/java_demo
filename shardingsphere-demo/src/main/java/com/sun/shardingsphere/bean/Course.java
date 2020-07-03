package com.sun.shardingsphere.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-03 10:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_course")
public class Course {
    
    private Long id;

    private String courseName;

    private Long userId;

}
