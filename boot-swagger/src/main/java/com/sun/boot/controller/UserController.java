package com.sun.boot.controller;

import com.sun.boot.bean.dto.UserDTO;
import com.sun.boot.bean.po.UserPO;
import com.sun.boot.bean.vo.UserVO;
import com.sun.boot.convert.UserConvert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-21 15:38
 */
@RestController
@RequestMapping("/users")
@Api(tags = {"用户 API 接口"}) // 标签
public class UserController {

    private List<UserPO> userPOList = new ArrayList<>();

    {
        userPOList.add(new UserPO().setId(1).setUsername("zhangsan"));
        userPOList.add(new UserPO().setId(2).setUsername("lis"));
        userPOList.add(new UserPO().setId(3).setUsername("wanger"));
    }

    /**
     * @ApiOperation:标记它是一个 API 操作
     * value: API 操作名
     * notes: API 操作的描述
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询用户列表", notes = "作为测试，所以返回用户全列表")
    public List<UserVO> list() {
        List<UserVO> userVOList = UserConvert.INSTANCE.convertVO(this.userPOList);
        return userVOList;
    }

    @GetMapping("/get")
    @ApiOperation(value = "获得指定用户编号的用户")
    @ApiImplicitParam(name = "id", value = "用户编号", paramType = "query", dataTypeClass = Integer.class, required = true, example = "1024")
    public UserVO get(@RequestParam("id") Integer id) {
        UserPO userPO = userPOList.get(id - 1);
        return UserConvert.INSTANCE.convertVO(userPO);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public Integer add(UserDTO userDTO) {
        userPOList.add(new UserPO().setId(userDTO.getId()).setUsername(userDTO.getUsername()));
        return 1;
    }

    @PostMapping("/update")
    @ApiOperation("更新指定用户编号的用户")
    public Boolean update(UserDTO userDTO) {
        return true;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除指定用户编号的用户")
    @ApiImplicitParam(name = "id", value = "用户编号", paramType = "query", dataTypeClass = Integer.class, required = true, example = "1024")
    public Boolean delete(@RequestParam("id") Integer id) {
        return true;
    }

}
