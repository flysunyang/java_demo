package com.sun.boot.convert;

import com.sun.boot.bo.UserBO;
import com.sun.boot.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 11:11
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserBO convert(UserDO userDO);
}
