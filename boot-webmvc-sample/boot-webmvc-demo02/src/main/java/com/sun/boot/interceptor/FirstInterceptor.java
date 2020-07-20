package com.sun.boot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaoyang
 * @description
 * 拦截器的常用功能
 * 日志拦截器，记录请求与响应。这样，我们可以知道每一次请求的参数，响应的结果，执行的时长等等信息。
 * 认证拦截器，我们可以解析前端传入的用户标识，例如说 access_token 访问令牌，获得当前用户的信息，记录到 ThreadLocal 中。这样，后续的逻辑，只需要通过 ThreadLocal 就可以获取到用户信息。
 * 授权拦截器，我们可以通过每个 API 接口需要的授权信息，进行判断，当前请求是否允许访问。例如说，用户是否登录，是否有该 API 操作的权限等等。
 * 限流拦截器，我们可以通过每个 API 接口的限流配置，进行判断，当前请求是否超过允许的请求频率，避免恶意的请求，打爆整个系统。
 *
 * // 伪代码
 * Exception ex = null;
 * try {
 *     // 前置处理
 *     if (!preHandle(request, response, handler)) {
 *         return;
 *     }
 *
 *     // 执行处理器，即执行 API 的逻辑
 *     handler.execute();
 *
 *     // 后置处理
 *     postHandle(request, response, handler);
 * } catch(Exception exception) {
 *     // 如果发生了异常，记录到 ex 中
 *     ex = exception;
 * } finally {
 *     afterCompletion(request, response, handler);
 * }
 *
 * @create 2020-07-17 16:59
 */
@Slf4j
public class FirstInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("[FirstInterceptor][preHandle][handler({})]", handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("[FirstInterceptor][postHandle][handler({})]", handler);
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("[FirstInterceptor][afterCompletion][handler({})]", handler, ex);
    }
    
}
