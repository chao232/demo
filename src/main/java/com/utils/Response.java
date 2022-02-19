package com.utils;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;
import org.springframework.util.Assert;

public class Response<T> implements Serializable {
    private static final long serialVersionUID = -618974407909870373L;
    public static String CODE_SUCCESS = "200";
    public static String CODE_FAIL = "-1";
    private String code;
    private String bizCode;
    private String msg;
    private T data;
    private List<String> errors;
    private boolean canRetry;
    private boolean success;
    private String errorLevel;
    private String errorType;

    public String toString() {
        return (new StringJoiner(", ", Response.class.getSimpleName() + "[", "]")).add("code=" + this.code + "").add("bizCode=" + this.bizCode + "").add("msg='" + this.msg + "'").add("data=" + this.data).add("canRetry=" + this.canRetry).add("success=" + this.success).add("errors=" + this.errors).toString();
    }

    public Response() {
        this.msg = "操作成功!";
        this.canRetry = false;
        this.success = true;
    }

    private Response(T data) {
        this("", data);
    }

    private Response(String msg, T data) {
        this.msg = "操作成功!";
        this.canRetry = false;
        this.success = true;
        this.setMsg(msg);
        this.setData(data);
    }

    public static <T> Response<T> ofSuccess(T data) {
        Response<T> response = new Response();
        response.code = CODE_SUCCESS;
        response.data = data;
        return response;
    }

    public static <T> Response<T> ofSuccess(String msg, T data) {
        Response<T> response = new Response();
        response.code = CODE_SUCCESS;
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> ofFailure(String code, String msg) {
        Assert.isTrue(!CODE_SUCCESS.equals(code), "code 必须是错误的！");
        Response<T> response = new Response();
        response.setCode(code);
        response.setBizCode(CODE_FAIL);
        response.setMsg(msg);
        response.setCanRetry(false);
        response.setSuccess(false);
        response.setErrorLevel("ERROR");
        response.setErrorType("BIZ");
        return response;
    }

    public static <T> Response<T> ofFailue(String msg) {
        Response<T> response = new Response();
        response.setCode(CODE_FAIL);
        response.setBizCode(CODE_FAIL);
        response.setMsg(msg);
        response.setCanRetry(false);
        response.setSuccess(false);
        response.setErrorLevel("ERROR");
        response.setErrorType("BIZ");
        return response;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBizCode() {
        return this.bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getErrorLevel() {
        return this.errorLevel;
    }

    public void setErrorLevel(String errorLevel) {
        this.errorLevel = errorLevel;
    }

    public String getErrorType() {
        return this.errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public boolean isCanRetry() {
        return this.canRetry;
    }

    public void setCanRetry(boolean canRetry) {
        this.canRetry = canRetry;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
