package com.sun.boot.convert;

import com.sun.boot.bo.StudentBO;
import com.sun.boot.dataobject.AddressDO;
import com.sun.boot.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 14:03
 */
@Mapper
public interface StudentConvert {

    StudentConvert INSTANCE = Mappers.getMapper(StudentConvert.class);

    /**
     * 复杂对象的转化
     * @param userDO
     * @param myDddressDO
     * @return
     */
    @Mappings(value = {
            @Mapping(source = "userDO.id", target = "id"),
            @Mapping(source = "userDO.username", target = "username"),
            @Mapping(source = "userDO.password", target = "password"),
            @Mapping(source = "myDddressDO.address", target = "addressDO.address"),
            @Mapping(source = "myDddressDO.userId", target = "addressDO.userId"),
    })
    StudentBO convert(UserDO userDO, AddressDO myDddressDO);
}
