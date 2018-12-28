package com.lyl.enums;

/**
 * Created by 潘淮  on 2018/12/28.<br>
 */
public enum  OrderEnum {

    zero(0,"待支付"),one(1,"超时"), two(2, "支付成功"),three(3,"交易关闭"),ten(10,"交易完成");

    private int code;

    private String msg;

    OrderEnum() {
    }

    OrderEnum(int code, String msg) {
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
