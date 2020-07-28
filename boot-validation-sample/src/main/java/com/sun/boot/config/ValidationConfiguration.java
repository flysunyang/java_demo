package com.sun.boot.config;

import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-27 18:53
 */
@Configuration
public class ValidationConfiguration {

    @Bean
    public Validator validator(MessageSource messageSource)  {
        // 创建 LocalValidatorFactoryBean 对象
        LocalValidatorFactoryBean validator = ValidationAutoConfiguration.defaultValidator();
        // 设置 messageSource 属性，实现 i18 国际化
        validator.setValidationMessageSource(messageSource);
        // 返回
        return validator;
    }

}
