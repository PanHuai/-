package com.lyl.enums;

/**
 * Created by 潘淮  on 2018/12/28.<br>
 */
public enum PayEnum {
    ALIPAY(1,"支付宝"), WX_JSAPI(2, "公众号"),WX_APP(3,"APP");

    private int code;

    private String msg;

    PayEnum() {
    }

    PayEnum(int code, String msg) {
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
