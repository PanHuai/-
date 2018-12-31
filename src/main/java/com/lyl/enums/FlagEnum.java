package com.lyl.enums;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
public enum FlagEnum {
    TRUE(1,"是"),FALSE(0,"否");

    private int code;

    private String msg;

    FlagEnum() {
    }

    FlagEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
