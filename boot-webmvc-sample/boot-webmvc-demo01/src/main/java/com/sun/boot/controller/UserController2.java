package com.sun.boot.controller;

import com.sun.boot.model.dto.UserDTO;
import com.sun.boot.model.vo.UserVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyang
 * @description 工作中常用如下
 * @create 2020-07-17 14:38
 */
@RestController
@RequestMapping("/users2")
public class UserController2 {

    List<UserVO> result = new ArrayList<>();

    {
        result.add(new UserVO().setId(1).setUsername("zhangsan"));
        result.add(new UserVO().setId(2).setUsername("lisi"));
        result.add(new UserVO().setId(3).setUsername("wanger"));
    }

    /**
     * 用户列表
     * @return
     */
    @GetMapping("/list")
    public List<UserVO> list() {
        return result;
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @GetMapping("/get")
    public UserVO get(@RequestParam("id") Integer id) {
        return result.get(id - 1);
    }

    @PostMapping("/add")
    public Integer add(UserDTO userDTO) {
        result.add(new UserVO().setId(userDTO.getId()).setUsername(userDTO.getUsername()));
        return 1;
    }

    @PostMapping("/update")
    public Boolean update(UserDTO userDTO) {
        UserVO userVO = result.get(userDTO.getId() - 1);
        userVO.setUsername(userDTO.getUsername() != null ? userDTO.getUsername() : userVO.getUsername());
        return true;
    }

    @DeleteMapping("/delete")
    public Boolean delete(@RequestParam("id") Integer id) {
        result.remove(id - 1);
        return true;
    }
}
