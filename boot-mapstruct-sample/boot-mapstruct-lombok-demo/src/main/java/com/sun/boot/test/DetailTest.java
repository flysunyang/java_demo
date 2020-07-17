package com.sun.boot.test;

import com.sun.boot.bo.UserBO;
import com.sun.boot.convert.UserConvert;
import com.sun.boot.dataobject.UserDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 13:48
 */
public class DetailTest {

    public static void main(String[] args) {
        /*UserDO userDO = new UserDO(1, "张三", "zhangsan");
        UserDetailBO userDetailBO = UserConvert.INSTANCE.convertDetailBO(userDO);
        System.out.println(userDetailBO);*/

        UserDO userDO = new UserDO(1, "张三", "zhangsan");
        UserDO userDO2 = new UserDO(2, "李四", "lisi");
        List<UserDO> userDOList = new ArrayList<>();
        userDOList.add(userDO);
        userDOList.add(userDO2);
        List<UserBO> userBOList = UserConvert.INSTANCE.convertList(userDOList);
        userBOList.stream().forEach(System.out::println);
    }
}
