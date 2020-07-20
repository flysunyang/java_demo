package com.sun.boot.convert;

import com.sun.boot.bean.po.UserDO;
import com.sun.boot.bean.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-20 17:15
 */
@Mapper
public interface UserConvert {

    public UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO convertVO(UserDO userDO);

    List<UserVO> convertVO(List<UserDO> userDO);
}
