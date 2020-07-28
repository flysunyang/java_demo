package com.sun.boot.dto;

import lombok.Data;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import java.io.Serializable;

/**
 * @author zhaoyang
 * @description 分组校验: 即相同的 Bean 对象，根据校验分组，使用不同的校验规则
 * @create 2020-07-27 18:28
 */
@Data
public class UserUpdateStatusDTO implements Serializable {

    /**
     * 分组 01 ，要求状态必须为 true
     */
    public interface GroupOne {}

    /**
     * 分组 01 ，要求状态必须为 false
     */
    public interface GroupTwo {}

    /**
     * 状态
     */
    @AssertTrue(message = "状态必须为 true", groups = GroupOne.class)
    @AssertFalse(message = "状态必须为 false", groups = GroupTwo.class)
    private Boolean status;
}
