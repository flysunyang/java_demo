package com.sun.boot.convert;

import com.sun.boot.bo.UserBO;
import com.sun.boot.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 10:02
 */
@Mapper
public interface UserConvert {

    UserConvert instance = Mappers.getMapper(UserConvert.class);

    UserBO convert(UserDO userDO);

}
