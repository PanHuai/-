package com.lyl.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by 潘淮  on 2018/12/28.<br>
 */
public class Order {

    private int id;  // 订单ID

    private String no;  // 系统生成的订单号

    private String pay_no;  // 支付平台生成的订单号

    private LocalDateTime createTime;  // 订单创建时间

    private LocalDateTime payTime;  // 订单支付时间

    private LocalDateTime beginTime;  // 订单开始时间

    private LocalDateTime endTime;  // 订单结束时间

    private BigDecimal payMoney;  // 订单实际支付金额

    private BigDecimal orig; // 原价

    private BigDecimal discountMoney; // 优惠金额

    private User user;  // 购买用户

    private int payType;  // 订单支付方式

    private int state;  // 订单状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPay_no() {
        return pay_no;
    }

    public void setPay_no(String pay_no) {
        this.pay_no = pay_no;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getOrig() {
        return orig;
    }

    public void setOrig(BigDecimal orig) {
        this.orig = orig;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
