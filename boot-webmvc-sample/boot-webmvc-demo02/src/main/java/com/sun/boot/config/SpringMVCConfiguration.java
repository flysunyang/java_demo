package com.sun.boot.config;

import com.sun.boot.interceptor.FirstInterceptor;
import com.sun.boot.interceptor.SecondInterceptor;
import com.sun.boot.interceptor.ThirdInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 17:04
 */
@Configuration
@Slf4j
public class SpringMVCConfiguration implements WebMvcConfigurer {

    @Bean
    public FirstInterceptor firstInterceptor() {
        return new FirstInterceptor();
    }

    @Bean
    public SecondInterceptor secondInterceptor() {
        return new SecondInterceptor();
    }

    @Bean
    public ThirdInterceptor thirdInterceptor() {
        return new ThirdInterceptor();
    }

    /**
     * 定义拦截器
     * @param registry
     */
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器一
        registry.addInterceptor(this.firstInterceptor()).addPathPatterns("/**");
        // 拦截器二
        registry.addInterceptor(this.secondInterceptor()).addPathPatterns("/users/current_user");
        // 拦截器三
        registry.addInterceptor(this.thirdInterceptor()).addPathPatterns("/**");
    }*/

    /**
     * 自定义listener
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean =
                new ServletListenerRegistrationBean<>(new ServletContextListener() {
                    @Override
                    public void contextInitialized(ServletContextEvent sce) {
                        // 程序启动时调用
                        log.info("[contextInitialized]");
                    }

                    @Override
                    public void contextDestroyed(ServletContextEvent sce) {
                        // 程序消亡时调用
                        log.info("[contextDestroyed]");
                    }

                });
        return listenerRegistrationBean;
    }

    /**
     * 自定义过滤器filter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                log.info("[doFilter]");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        });
        return filterFilterRegistrationBean;
    }

    /**
     * 自定义servlet
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                log.info("[doGet][uri: {}]", req.getRequestURI());
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().print("我是自定义servlet");
            }
        });
        registrationBean.setUrlMappings(Collections.singleton("/test/servlet"));
        return registrationBean;
    }
}
