package com.sun.boot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhaoyang
 * @description 自定义约束校验器
 * @create 2020-07-27 17:46
 */
public class InEnumValidator implements ConstraintValidator<InEnum, Integer> {

    /**
     * 值数组
     */
    private Set<Integer> values;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (values.contains(value)) {
            return true;
        }
        // 校验不通过，自定义提示语句（因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
        // 禁用默认的 message 的值
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
                // 重新添加错误提示语句
                .replaceAll("\\{value}", values.toString())).addConstraintViolation();
        return false;
    }

    @Override
    public void initialize(InEnum annotation) {
        IntArrayValuable[] values = annotation.value().getEnumConstants();
        if (values.length == 0) {
            this.values = Collections.emptySet();
        } else {
            this.values = Arrays.stream(values[0].array()).boxed().collect(Collectors.toSet());
        }
    }
}
