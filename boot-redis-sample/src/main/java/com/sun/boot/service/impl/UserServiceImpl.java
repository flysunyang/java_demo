package com.sun.boot.service.impl;

import com.sun.boot.mapper.UserMapper;
import com.sun.boot.pojo.CommonResult;
import com.sun.boot.pojo.User;
import com.sun.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-13 10:23
 */
@Service
@CacheConfig(cacheNames = {"user_cache"})
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public CommonResult<Integer> addOne(User user) {
        int result = userMapper.insert(user);
        Assert.state(result > 0, "新增失败，请联系管理员~");
        return new CommonResult<>(200, "success", result);
    }

    /**
     * @Cacheable 查找缓存 - 有就返回 -没有就执行方法体 - 将结果缓存起来；
     * @param id
     * @return
     */
    @Cacheable(key = "#id")
    @Override
    public CommonResult<User> getById(Long id) {
        User user = userMapper.selectById(id);
        Assert.notNull(user, "根据ID：" + id + "查询不到数据");
        return new CommonResult<>(200, "success", user);
    }

    /**
     * @CachePut 执行方法体 - 将结果缓存起来
     * @param user
     * @return
     */
    @CachePut(key = "#user.id")
    @Override
    public CommonResult<User> updateOneById(User user) {
        int result = userMapper.updateById(user);
        Assert.state(result > 0, "更新失败，请联系管理员~");
        User updateUser = userMapper.selectById(user.getId());
        return new CommonResult<>(200, "success", updateUser);
    }

    /**
     * @CacheEvict 删除缓存
     *  allEntries: 会删除所有跟user_cache相关的key，默认是false
     * @param id
     * @return
     */
    @CacheEvict(key = "#id", allEntries = true)
    @Override
    public CommonResult<Integer> deleteById(Long id) {
        int result = userMapper.deleteById(id);
        Assert.state(result > 0, "删除失败，请联系管理员~");
        return new CommonResult<>(200, "success", result);
    }

}
