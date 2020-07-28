package com.sun.boot.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-27 13:41
 */
@Data
@Accessors(chain = true)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -2948828151181852421L;

    @NotEmpty(message = "登陆姓名不能为空")
    @Length(min = 5, max = 16, message = "用户长度在5-16之间")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;

    @NotEmpty(message = "登陆密码不能为空")
    @Size(min = 5, max = 10, message = "密码长度在5-10之间")
    private String password;

}
