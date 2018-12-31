package com.lyl.service;

import com.lyl.model.Cart;

import java.util.List;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
public interface CartService {

    public List<Cart> getByUserAndActive(int id,int active_id);

    public int add(List<Cart> carts);

    public int update(Cart cart);

    public int updateChecked(int active_id, int user_id,Cart cart);

    public Cart getByUserActiveChecked(int user_id, int active_id);

    public Cart getByUserAndProduct(int user_id, int product_id);

    public Cart getById(int id);
}
