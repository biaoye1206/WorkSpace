package com.tianj.autowash.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tianj.autowash.constant.ResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 响应对象
 *
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-03-11 10:26
 */

@ApiModel("响应结果")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = 3378347416053033829L;
    /**
     * 响应状态码
     */
    @ApiModelProperty("响应状态码")
    private Integer code;
    /**
     * 响应消息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("响应消息")
    private String msg;
    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private T body;

    public ResponseEntity() {
        this.code = ResponseCode.OK;
        this.msg = "SUCCESS";
    }

    public ResponseEntity(T body) {
        this();
        this.body = body;
    }

    public ResponseEntity(Integer code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public ResponseEntity(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseEntity setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResponseEntity setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseEntity setBody(T data) {
        this.body = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getBody() {
        return body;
    }
}
