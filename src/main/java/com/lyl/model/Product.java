package com.lyl.model;

import java.math.BigDecimal;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
public class Product {

    private int id;

    private String name;

    private BigDecimal orig;

    private int type;

    private int currentNum;

    private String ps;

    private Active active;

    public Product() {
    }

    public Product(int id, String name, BigDecimal orig, int type, String ps,int currentNum,Active active) {
        this.id = id;
        this.name = name;
        this.orig = orig;
        this.type = type;
        this.ps = ps;
        this.currentNum = currentNum;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOrig() {
        return orig;
    }

    public void setOrig(BigDecimal orig) {
        this.orig = orig;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }
}
