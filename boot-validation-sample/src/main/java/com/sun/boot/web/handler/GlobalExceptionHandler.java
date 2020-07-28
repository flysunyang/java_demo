package com.sun.boot.web.handler;

import com.sun.boot.vo.CommonResult;
import com.sun.boot.web.constants.ServiceExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author zhaoyang
 * @description 全局异常处理
 * @create 2020-07-27 16:55
 */
@ControllerAdvice(basePackages = {"com.sun.boot.controller"})
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {BindException.class})
    public CommonResult bindExceptionHandler(HttpServletRequest req, BindException ex) {
        log.debug("[bindExceptionHandler]", ex);
        StringBuffer sb = new StringBuffer();
        List<ObjectError> allErrors = ex.getAllErrors();
        for (ObjectError allError : allErrors) {
            if(sb.length() > 0) {
                sb.append(";");
            }
            sb.append(allError.getDefaultMessage());
        }
        return CommonResult.error(ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getCode(),
                ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getMessage() + ":" + sb.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public CommonResult constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        log.debug("[constraintViolationExceptionHandler]", ex);
        StringBuffer sb = new StringBuffer();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            if (sb.length() > 0) {
                sb.append(";");
            }
            sb.append(constraintViolation.getMessage());
        }
        return CommonResult.error(ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getCode(),
                ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getMessage() + ":" + sb.toString());
    }

}
