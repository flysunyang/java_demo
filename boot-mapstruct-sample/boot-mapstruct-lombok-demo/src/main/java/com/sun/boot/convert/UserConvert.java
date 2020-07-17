package com.sun.boot.convert;

import com.sun.boot.bo.UserBO;
import com.sun.boot.bo.UserDetailBO;
import com.sun.boot.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhaoyang
 * @description 对象转化
 * @create 2020-07-17 11:11
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserBO convert(UserDO userDO);

    /**
     * 当字段名不一致时
     * @param userDO
     * @return
     */
    @Mappings(value = {
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "name"),
            @Mapping(source = "password", target = "pwd")
    })
    UserDetailBO convertDetailBO(UserDO userDO);

    /**
     * 集合转化
     * @param userDOList
     * @return
     */
    List<UserBO> convertList(List<UserDO> userDOList);
}
