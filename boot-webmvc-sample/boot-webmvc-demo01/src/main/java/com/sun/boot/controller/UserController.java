package com.sun.boot.controller;

import com.sun.boot.model.dto.UserDTO;
import com.sun.boot.model.vo.UserVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyang
 * @description 标准的RESTFULL风格接口
 * @create 2020-07-17 14:38
 */
@RestController
@RequestMapping("/users")
public class UserController {

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
    @GetMapping("")
    public List<UserVO> list() {
        return result;
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public UserVO get(@PathVariable("id") Integer id) {
        return result.get(id - 1);
    }

    @PostMapping("")
    public Integer add(UserDTO userDTO) {
        result.add(new UserVO().setId(userDTO.getId()).setUsername(userDTO.getUsername()));
        return 1;
    }

    @PutMapping("/{id}")
    public Boolean update(@PathVariable("id") Integer id, UserDTO userDTO) {
        UserVO userVO = result.get(id - 1);
        userVO.setId(userDTO.getId() != null ? userDTO.getId() : userVO.getId());
        userVO.setUsername(userDTO.getUsername() != null ? userDTO.getUsername() : userVO.getUsername());
        return true;
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Integer id) {
        result.remove(id - 1);
        return true;
    }
}
