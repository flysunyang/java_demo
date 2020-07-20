package com.sun.boot.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-20 11:38
 */
@WebFilter(urlPatterns = {"/test/servlet2"})
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("this is MyFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
