package com.tianj.autowash.utils;

import com.tianj.autowash.basic.BaseEntity;
import com.tianj.autowash.exception.ValidateException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 数据验证工具
 *
 * @author Zhangxq
 * @version v1.0
 * @Date 2019-03-13 09:15
 */
public class BeanVlidator {
    /**
     * 验证
     *
     * @param target 被验证对象
     * @param groups 验证组
     */
    public static <T extends BaseEntity> void validate(T target, Class<?>... groups) throws ValidateException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> violations;
        if (groups.length > 0) {
            violations = validator.validate(target, groups);
        } else {
            violations = validator.validate(target);
        }
        if (!violations.isEmpty()) {
            throw new ValidateException("参数验证失败", buildError(violations));
        }

    }

    /**
     * 组合错误信息
     *
     * @param violations 验证返回错误对象
     * @return 错误信息集合
     */
    private static <T extends BaseEntity> Map<String, String> buildError(Set<ConstraintViolation<T>> violations) {
        Map error = new TreeMap();
        for (ConstraintViolation<T> violation : violations) {
            String key = violation.getPropertyPath().toString();
            String value = violation.getMessage();
            error.put(key, value);
        }
        return error;
    }
}
