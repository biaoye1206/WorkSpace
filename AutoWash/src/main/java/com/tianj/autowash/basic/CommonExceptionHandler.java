package com.tianj.autowash.basic;

import com.tianj.autowash.constant.ResponseCode;
import com.tianj.autowash.exception.CommonException;
import com.tianj.autowash.exception.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 全局异常处理
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-03-12 20:21
 */
@ControllerAdvice
public class CommonExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /**
     * 通用异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ResponseEntity commonException(CommonException e){
        return new ResponseEntity(e.getResponseStatus(),e.getMessage());
    }
    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler({ValidateException.class})
    @ResponseBody
    public ResponseEntity verifyErrorException(ValidateException e) {
        if (e.getValidates().isEmpty()) {
            return new ResponseEntity<>(ResponseCode.FAIL, e.getMessage());
        }
        return new ResponseEntity<>(ResponseCode.FAIL, e.getMessage(), e.getValidates());
    }

    /**
     * 拦截Exception类的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity exceptionHandler(Exception e) {
        e.printStackTrace();
        String msg = e.getLocalizedMessage();
        if (e instanceof MethodArgumentTypeMismatchException){
            msg = "参数类型错误，请检查参数";
        }
       if (e instanceof  NullPointerException ){
           msg = "系统错误，请稍后在试";
           log.error("系统错误",e);
       }
        if (e instanceof  ClassCastException ){
            msg = "系统错误，请稍后在试";
            log.error("系统错误",e);
        }
        return new ResponseEntity<>(ResponseCode.FAIL, msg);
    }

}
