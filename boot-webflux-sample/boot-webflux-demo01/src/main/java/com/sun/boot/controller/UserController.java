package com.sun.boot.controller;

import com.sun.boot.bean.dto.UserDTO;
import com.sun.boot.bean.po.UserDO;
import com.sun.boot.bean.vo.UserVO;
import com.sun.boot.convert.UserConvert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-20 16:21
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private List<UserDO> UserDOList = new ArrayList<>();

    {
        UserDOList.add(new UserDO().setId(1).setUsername("zhangsan"));
        UserDOList.add(new UserDO().setId(2).setUsername("lisi"));
        UserDOList.add(new UserDO().setId(3).setUsername("wanger"));
    }

    @PostMapping("/update")
    public Mono<Integer> update(UserDTO userDTO) {
        UserDO UserDO = UserDOList.get(userDTO.getId() - 1);
        UserDO.setUsername(userDTO.getUsername());
        return Mono.just(1);
    }

    @DeleteMapping("/delete")
    public Mono<Boolean> delete(@RequestParam("id") Integer id) {
        UserDOList.remove(id - 1);
        return Mono.just(true);
    }

    @PostMapping("/add")
    public Mono<Integer> add(UserDTO userDTO) {
        UserDOList.add(new UserDO().setId(userDTO.getId()).setUsername(userDTO.getUsername()));
        return Mono.just(1);
    }

    /**
     * 用户列表
     * @return
     */
    @GetMapping("/list")
    public Flux<UserVO> list() {
        List<UserVO> userVOList = UserConvert.INSTANCE.convertVO(UserDOList);
        return Flux.fromIterable(userVOList);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/get")
    public Mono<UserVO> get(@RequestParam("id") Integer id) {
        UserDO UserDO = UserDOList.get(id - 1);
        UserVO userVO = UserConvert.INSTANCE.convertVO(UserDO);
        return Mono.just(userVO);
    }
}
