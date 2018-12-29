package com.lyl.service.Impl;

import com.lyl.enums.OrderEnum;
import com.lyl.enums.PayEnum;
import com.lyl.mapper.OrderMapper;
import com.lyl.model.Order;
import com.lyl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by 潘淮  on 2018/12/28.<br>
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getById(int id) {
        return orderMapper.getById(id);
    }

    @Override
    @Transactional
    public int wxPaySuccess(String pay_no, String no) {
        Order order = orderMapper.getByNo(no);
        if (order.getState() == OrderEnum.zero.getCode()){
            order.setState(OrderEnum.two.getCode());
            order.setPayType(PayEnum.WX_JSAPI.getCode());
            order.setPay_no(pay_no);
            order.setPayTime(LocalDateTime.now());
            orderMapper.update(order);
        }else {
            // 退款操作
            if (order.getState() == OrderEnum.one.getCode()){
                order.setState(OrderEnum.three.getCode());
            }
        }
        return 0;
    }

    @Override
    public int add(Order order) {
        return orderMapper.add(order);
    }
}
