package com.sun.boot.test;

import com.sun.boot.bo.StudentBO;
import com.sun.boot.convert.StudentConvert;
import com.sun.boot.dataobject.AddressDO;
import com.sun.boot.dataobject.UserDO;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 13:48
 */
public class StudentTest {

    public static void main(String[] args) {
        UserDO userDO = new UserDO(1, "张三", "zhangsan");
        AddressDO addressDO = new AddressDO(100, "安徽省合肥市", 1);
        StudentBO studentBO = StudentConvert.INSTANCE.convert(userDO, addressDO);
        System.out.println(studentBO);
    }
}
