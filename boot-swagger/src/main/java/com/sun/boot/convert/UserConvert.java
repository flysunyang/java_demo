package com.sun.boot.convert;

import com.sun.boot.bean.po.UserPO;
import com.sun.boot.bean.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-21 15:49
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO convertVO(UserPO userPO);

    List<UserVO> convertVO(List<UserPO> userPOList);
}
