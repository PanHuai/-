package com.lyl.service.Impl;

import com.lyl.mapper.CartMapper;
import com.lyl.model.Cart;
import com.lyl.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
@Service("CartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> getByUserAndActive(int id,int active_id) {
        return cartMapper.getByUserAndActive(id,active_id);
    }

    @Override
    @Transactional
    public int add(List<Cart> carts) {
        return cartMapper.add(carts);
    }

    @Override
    @Transactional
    public int update(Cart cart) {
        return cartMapper.update(cart);
    }

    @Override
    @Transactional
    public int updateChecked(int active_id, int user_id,Cart cart) {
        cartMapper.updateChecked(active_id, user_id);
        return cartMapper.update(cart);
    }

    @Override
    public Cart getByUserActiveChecked(int user_id, int product_id) {
        return cartMapper.getByUserActiveChecked(user_id,product_id);
    }

    @Override
    public Cart getByUserAndProduct(int user_id, int product_id) {
        return cartMapper.getByUserAndProduct(user_id,product_id);
    }

    @Override
    public Cart getById(int id) {
        return cartMapper.getById(id);
    }
}
