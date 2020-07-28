package com.sun.boot.web.exception;

import com.sun.boot.web.constants.ServiceExceptionEnum;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-27 16:43
 */
public final class ServiceException extends RuntimeException {

    private Integer code;

    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        super(serviceExceptionEnum.getMessage());
        this.code = serviceExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

}
