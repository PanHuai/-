package com.lyl.controller;

import com.lyl.enums.OrderEnum;
import com.lyl.model.Order;
import com.lyl.service.OrderService;
import com.lyl.utils.RandomString;
import com.lyl.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
@Controller
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 我的订单详情
     */
    @RequestMapping("/api/user/order/list")
    public Response getAll(@RequestBody Map<String, Object> map) {
        return null;
    }

    /**
     * 新增订单
     */
    @RequestMapping("/api/user/order/add")
    @ResponseBody
    public Response add(@RequestBody Map<String, String> map){
        /*int userId = Integer.valueOf(map.get("userId"));
        BigDecimal orig = new BigDecimal(map.get("orig"));
        BigDecimal payMoney = new BigDecimal(map.get("payMoney"));*/
        Order order = new Order();
        order.setState(OrderEnum.zero.getCode());
        order.setCreateTime(LocalDateTime.now());
        order.setNo(RandomString.getInstance().generate());
        int orderId = orderService.add(order);
        logger.info("操作成功 订单ID："+ orderId);
        Response response = new Response();
        response.setCode(200);
        response.setMsg("操作成功");
        response.setData(orderId);
        return response;
    }

}
