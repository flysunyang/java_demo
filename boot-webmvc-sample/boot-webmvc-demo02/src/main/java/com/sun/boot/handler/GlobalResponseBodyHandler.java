package com.sun.boot.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.boot.model.vo.CommonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author zhaoyang
 * @description 使用AOP的思想，对controller层进行统一的返回值处理
 * @create 2020-07-17 16:05
 */
@ControllerAdvice(basePackages = {"com.sun.boot.controller"})
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    /**
     * 拦截 Controller 所有 API 接口的返回结果
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * 对拦截的结果进行封装为CommonResult对象
     * @param obj Controller中请求返回的结果
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(obj instanceof CommonResult) {
            return obj;
        }
        // 针对String需要特殊处理，否则会报ClassCastException，
        // 提示com.sun.boot.model.vo.CommonResult cannot be cast to java.lang.String
        if (obj instanceof String) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String result = objectMapper.writeValueAsString(obj);
                return result;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return CommonResult.success(obj);
    }
}
