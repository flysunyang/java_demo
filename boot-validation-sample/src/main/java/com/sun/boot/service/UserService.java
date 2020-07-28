package com.sun.boot.service;

import com.sun.boot.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author zhaoyang
 * @description 可以在Controller层不做验证，但是一定需要在Service层做验证，更加安全可靠
 * @create 2020-07-27 16:13
 */
@Service
@Slf4j
@Validated
public class UserService {

    public void get(@Min(value = 1L, message = "编号必须大于 0") Integer id) {
        log.info("[get][id: {}]", id);
    }

    public void add(@Valid UserDTO userDTO) {
        log.info("[add][addDTO: {}]", userDTO);
    }

    public void add01(UserDTO userDTO) {
        this.add(userDTO);
    }
}
