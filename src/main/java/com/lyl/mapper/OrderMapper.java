package com.lyl.mapper;

import com.lyl.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 潘淮  on 2018/12/28.<br>
 */
@Repository
public interface OrderMapper {

    public Order getById(@Param("id") int id);

    public Order getByNo(@Param("no") String no);

    public int update(Order order);

    public int add(Order order);
}
