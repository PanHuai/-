package com.lyl.service;

import com.lyl.model.Order;

/**
 * Created by 潘淮  on 2018/12/28.<br>
 */
public interface OrderService {

    public Order getById(int id);

    public int wxPaySuccess(String pay_no,String no);

    public int add(Order order);
}
