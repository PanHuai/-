package com.lyl.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
public class Meun_Dto {

    private String type;

    private String name;

    private String key;

    private List sub_button;

    public Meun_Dto() {
        this.sub_button = new ArrayList();
    }

    public Meun_Dto(String type, String name, String key, List sub_button) {
        this.type = type;
        this.name = name;
        this.key = key;
        this.sub_button = sub_button;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List getSub_button() {
        return sub_button;
    }

    public void setSub_button(List sub_button) {
        this.sub_button = sub_button;
    }
}
