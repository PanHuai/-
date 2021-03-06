package com.lyl.controller;

import com.lyl.enums.OrderEnum;
import com.lyl.model.Order;
import com.lyl.model.User;
import com.lyl.service.OrderService;
import com.lyl.service.UserService;
import com.lyl.utils.RandomString;
import com.lyl.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private UserService userService;

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
    public Response add(@RequestBody Map<String, String> map, HttpServletRequest request){
        /*int userId = Integer.valueOf(map.get("userId"));
        BigDecimal orig = new BigDecimal(map.get("orig"));
        BigDecimal payMoney = new BigDecimal(map.get("payMoney"));*/
        User user = (User) request.getSession().getAttribute("user");
        Order order = new Order();
        order.setState(OrderEnum.zero.getCode());
        order.setCreateTime(LocalDateTime.now());
        order.setNo(RandomString.getInstance().generate());
        order.setUser(user);
        orderService.add(order);
        logger.info("订单生成成功 订单ID："+ order.getId()+" 订单号："+order.getNo());
        Response response = new Response();
        response.setCode(200);
        response.setMsg("操作成功");
        response.setData(order.getId());
        return response;
    }

}
