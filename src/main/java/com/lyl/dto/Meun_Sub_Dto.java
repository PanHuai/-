package com.lyl.dto;

import java.util.List;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
public class Meun_Sub_Dto {

    private String name;

    private List<Meun_Dto> sub_button;

    public Meun_Sub_Dto() {
    }

    public Meun_Sub_Dto(String name, List<Meun_Dto> sub_button) {
        this.name = name;
        this.sub_button = sub_button;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Meun_Dto> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Meun_Dto> sub_button) {
        this.sub_button = sub_button;
    }
}
