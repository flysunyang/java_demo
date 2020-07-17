package com.sun.boot.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 13:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AddressDO implements Serializable {

    private Integer id;

    private String address;

    private Integer userId;

}
