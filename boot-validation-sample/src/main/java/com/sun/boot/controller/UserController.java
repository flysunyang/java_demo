package com.sun.boot.controller;

import com.sun.boot.dto.UserDTO;
import com.sun.boot.dto.UserUpdateDTO;
import com.sun.boot.dto.UserUpdateGenderDTO;
import com.sun.boot.dto.UserUpdateStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;


/**
 * @author zhaoyang
 * @description
 * @create 2020-07-27 14:08
 */
@RestController
@RequestMapping("/users")
@Slf4j
@Validated
public class UserController {

    @PostMapping("/update")
    public void update(@Valid UserUpdateDTO updateDTO) {
        log.info("[update][updateDTO: {}]", updateDTO);
    }

    @PostMapping("/update_status_true")
    public void updateStatusTrue(@Validated(UserUpdateStatusDTO.GroupOne.class) UserUpdateStatusDTO updateStatusDTO) {
        log.info("[updateStatusTrue][updateStatusDTO: {}]", updateStatusDTO);
    }

    @PostMapping("/update_status_false")
    public void updateStatusFalse(@Validated(UserUpdateStatusDTO.GroupTwo.class) UserUpdateStatusDTO updateStatusDTO) {
        log.info("[updateStatusFalse][updateStatusDTO: {}]", updateStatusDTO);
    }

    @PostMapping("/update_gender")
    public void updateGender(@Valid UserUpdateGenderDTO updateGenderDTO) {
        log.info("[updateGender][updateGenderDTO: {}]", updateGenderDTO);
    }

    @GetMapping("/test")
    public String test(Integer id) {
        log.info("[get][id: {}]", id);
        return String.valueOf(id);
    }

    @GetMapping("/get")
    public void get(@RequestParam("id") @Min(value = 1L, message = "编号必须大于 0") Integer id) {
        log.info("[get][id: {}]", id);
    }

    @PostMapping("/add")
    public Integer add(@Valid UserDTO userDTO) {
        log.info("userDTO：" + userDTO);
        return 1;
    }
}
