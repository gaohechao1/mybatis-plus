package com.east.cloud.mybatisplus.utils;

import java.io.Serializable;

public class JsonResult implements Serializable {
    private static final long serialVersionUID = -4800793124936904868L;

    /**
     * 成功
     */
    public final static int SUCCESS = 10000;
    /**
     * 失败
     */
    public final static int FAILED = 10001;
    /**
     * 验证码错误
     */
    public final static int VERIFYCODEFAILED = 10002;
    /**
     * 登录名或者密码错误
     */
    public final static int LOGNAMEORPQWDFAILED = 10003;
    /**
     * token校验失败
     */
    public final static int TOKENERROR = 10004;
    /**
     * 系统异常
     */
    public final static int SYSTEMERROR = 10005;

    /**
     * 返回是否成功的状态,10000表示成功,
     * 10001 或其他值 表示失败
     */
    private int state;
    /**
     * 成功时候,返回的JSON数据
     */
    private Object data;
    /**
     * 是错误时候的错误消息
     */
    private String message;

    public JsonResult() {
    }

    public JsonResult(int state, Object data, String message) {
        this.state = state;
        this.data = data;
        this.message = message;
    }

    public JsonResult(Throwable e){
        state = SYSTEMERROR;
        data=null;
        message=e.getMessage();
    }

    public JsonResult(Object data){
        state = SUCCESS;
        this.data=data;
        message="";
    }

    public int getState() {
        return state;
    }


    public void setState(int state) {
        this.state = state;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "JsonResult [state=" + state + ", data=" + data + ", message=" + message + "]";
    }
}
