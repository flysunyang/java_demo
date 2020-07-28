package com.sun.boot.convert;

import com.sun.boot.dto.UserDTO;
import com.sun.boot.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-27 14:11
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO convertVo(UserDTO userDTO);

}
