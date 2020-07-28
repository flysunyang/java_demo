package com.sun.boot.web.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.boot.vo.CommonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author zhaoyang
 * @description 全局返回值处理
 * @create 2020-07-27 16:55
 */
@ControllerAdvice(basePackages = {"com.sun.boot.controller"})
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof CommonResult) {
            return body;
        }
        if(body instanceof String) {
            ObjectMapper objectMapper = new ObjectMapper();
            String res = null;
            try {
                res = objectMapper.writeValueAsString(body);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return res;
        }
        return CommonResult.success(body);
    }
}
