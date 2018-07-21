/*  
 * Copyright 2010-2017 Tocean Group.  
 * 版权：商业代码，未经许可，禁止任何形式拷贝、传播及使用
 * 文件名：Result.java
 * 描述：  
 * 修改人：Arber.Lee  
 * 修改时间：2018年5月9日  
 * 跟踪单号：  
 * 修改单号：  
 * 修改内容：  
 */
package com.example.springjpa.compoment;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 */
public class Result {
    private int code;
    private String message;
    private Object data;
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    // 成功
    private static final Integer SUCCESS = 200;
    // 失败
    private static final Integer FAIL = 400;
    // 接口不存在
    private static final Integer NOT_FOUND = 404;
    // 未认证（签名错误）
    private static final Integer UNAUTHORIZED = 401;
    // 服务器内部错误
    private static final Integer INTERNAL_SERVER_ERROR = 500;


    public static Result genSuccessResult(Object data) {
        return new Result().setCode(SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result().setCode(FAIL).setMessage(message);
    }



    public Result setCode(Integer resultCode) {
        this.code = resultCode;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
