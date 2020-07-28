package com.sun.boot;

import com.sun.boot.dto.UserDTO;
import com.sun.boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


/**
 * @author zhaoyang
 * @description
 * @create 2020-07-27 16:14
 */
@SpringBootTest
public class ValidationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private Validator validator;

    @Test
    public void testValidator() {
        // 创建 UserAddDTO 对象
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("zs");
        userDTO.setPassword("pwd");
        Set<ConstraintViolation<UserDTO>> validateRes = validator.validate(userDTO);
        for (ConstraintViolation<UserDTO> constraintViolation : validateRes) {
            // 属性:消息
            System.out.println(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
        }
    }

    @Test
    public void test3() {
        userService.add01(new UserDTO().setUsername("zs").setPassword("pwd"));
    }

    @Test
    public void test2() {
        userService.add(new UserDTO().setUsername("zs").setPassword("pwd"));
    }

    @Test
    public void test1() {
        userService.get(0);
    }
}
