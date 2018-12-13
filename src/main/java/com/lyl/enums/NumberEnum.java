package com.lyl.enums;

/**
 * Created by 潘淮  on 2018/12/13.<br>
 */
public enum NumberEnum {

    disabled(0,"false"),enabled(1,"true");

    private int key;

    private String value;

    NumberEnum() {
    }

    NumberEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
