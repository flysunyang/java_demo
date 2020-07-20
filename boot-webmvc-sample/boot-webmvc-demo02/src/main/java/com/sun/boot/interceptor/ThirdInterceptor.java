package com.sun.boot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 17:01
 */
@Slf4j
public class ThirdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("[ThirdInterceptor][preHandle][handler({})]", handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("[ThirdInterceptor][postHandle][handler({})]", handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("[ThirdInterceptor][afterCompletion][handler({})]", handler, ex);
        throw new RuntimeException("故意抛个错误");
    }
}
