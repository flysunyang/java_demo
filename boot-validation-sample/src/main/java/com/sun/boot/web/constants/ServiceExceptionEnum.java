package com.sun.boot.web.constants;

/**
 * @author zhaoyang
 * @description
 * 建议10位
 * 第一段：1位，类型
 * 1 - 系统级别错误
 * 2 - 业务级别错误
 * 第二段：3位，系统类型
 * 第三段：3位，模块类型
 * 第四段：3位，错误码
 *
 * @create 2020-07-27 16:37
 */
public enum ServiceExceptionEnum {

    // 系统级别
    SUCCESS(0, "成功"),
    SYS_ERROR(2001001000, "服务端发生异常"),
    MISSING_REQUEST_PARAM_ERROR(2001001001, "参数缺失"),

    // 用户模块
    USER_NOT_FOUND(1001002000, "用户不存在"),

    // 订单模块

    // 其余模块
    INVALID_REQUEST_PARAM_ERROR(2001001002, "请求参数不合法"),
    ;

    private Integer code;

    private String message;

    ServiceExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
