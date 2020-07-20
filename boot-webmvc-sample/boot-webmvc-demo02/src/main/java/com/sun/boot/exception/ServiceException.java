package com.sun.boot.exception;

import com.sun.boot.constants.ServiceExceptionEnum;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 16:24
 */
public final class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        // 使用父类的 message 字段
        super(serviceExceptionEnum.getMessage());
        // 设置错误码
        this.code = serviceExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

}
