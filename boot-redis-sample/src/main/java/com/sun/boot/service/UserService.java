package com.sun.boot.service;

import com.sun.boot.pojo.CommonResult;
import com.sun.boot.pojo.User;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-13 10:08
 */
public interface UserService {

    CommonResult<Integer> addOne(User user);

    CommonResult<User> getById(Long id);

    CommonResult<User> updateOneById(User user);

    CommonResult<Integer> deleteById(Long id);

}
