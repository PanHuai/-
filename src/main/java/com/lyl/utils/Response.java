package com.lyl.utils;

import java.io.Serializable;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
public class Response implements Serializable{

    private int code;

    private String msg;

    private Object data;

    public Response() {
    }

    public Response(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
