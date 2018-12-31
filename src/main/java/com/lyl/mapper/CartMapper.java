package com.lyl.mapper;

import com.lyl.model.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
@Repository
public interface CartMapper {

    public List<Cart> getByUserAndActive(@Param("user_id") int id,@Param("active_id") int active_id);

    public int add(List<Cart> carts);

    public int update(Cart cart);

    public int updateChecked(@Param("active_id") int active_id, @Param("user_id") int user_id);

    public Cart getByUserActiveChecked(@Param("user_id") int user_id, @Param("active_id") int active_id);

    public Cart getByUserAndProduct(@Param("user_id") int user_id, @Param("product_id") int product_id);

    public Cart getById(@Param("id") int id);
}