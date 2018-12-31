package com.lyl.service;

import com.lyl.model.Product;

import java.util.List;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
public interface ProductService {

    public List<Product> getOrigByActive(int id);

    public List<Product> getProductsByActive(int id);

    public Product getByProductId(int id);

    public Product getByTypeAndActive(int active_id, int type);
}
