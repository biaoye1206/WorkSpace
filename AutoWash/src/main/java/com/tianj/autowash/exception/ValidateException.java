package com.tianj.autowash.exception;

import java.util.Collections;
import java.util.Map;

/**
 * 参数验证异常
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-03-13 10:23
 */
public class ValidateException extends RuntimeException {
    private Map<String, String> validates = Collections.EMPTY_MAP;
    public ValidateException(Map<String, String> validates) {
        this.validates = validates;
    }
    public ValidateException(String message) {
        super(message);
    }
    public ValidateException(String message, Map validates) {
        super(message);
        this.validates = validates;
    }
    public Map<String, String> getValidates() {
        return validates;
    }
}
