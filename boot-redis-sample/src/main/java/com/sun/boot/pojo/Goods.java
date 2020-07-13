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
 * @create 2020-07-13 11:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_goods")
public class Goods implements Serializable {
    
    private static final long serialVersionUID = -8118666609395904603L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String goodsName;

    private Double price;

}
