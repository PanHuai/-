package com.lyl.model;

import java.time.LocalDateTime;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
public class Cart {

    private int id;

    private User user;

    private Active active;

    private Product product;

    private int count;

    private int state;

    private int checked;

    private LocalDateTime beginTime;

    public Cart() {
    }

    public Cart(int id, User user, Active active, int count, int state, int checked, LocalDateTime beginTime,Product product) {
        this.id = id;
        this.user = user;
        this.active = active;
        this.count = count;
        this.state = state;
        this.checked = checked;
        this.product = product;
        this.beginTime = beginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
