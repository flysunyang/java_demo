package com.sun.boot.handler;

import com.sun.boot.constants.ServiceExceptionEnum;
import com.sun.boot.exception.ServiceException;
import com.sun.boot.model.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 16:26
 */
@ControllerAdvice(basePackages = {"com.sun.boot.controller"})
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理 ServiceException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        log.error("[serviceExceptionHandler]", ex);
        // 包装 CommonResult 结果
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理 MissingServletRequestParameterException 异常
     * SpringMVC 参数不正确
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException ex) {
        log.error("[missingServletRequestParameterExceptionHandler]", ex);
        // 包装 CommonResult 结果
        return CommonResult.error(ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getCode(),
                ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
    }

    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(HttpServletRequest req, Exception e) {
        // 记录异常日志
        log.error("[exceptionHandler]", e);
        // 返回 ERROR CommonResult
        return CommonResult.error(ServiceExceptionEnum.SYS_ERROR.getCode(),
                ServiceExceptionEnum.SYS_ERROR.getMessage());
    }

}
