package com.lyl.service.Impl;

import com.lyl.mapper.ProductMapper;
import com.lyl.model.Product;
import com.lyl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> getOrigByActive(int id) {
        return productMapper.getOrigByActive(id);
    }

    @Override
    public List<Product> getProductsByActive(int id) {
        return productMapper.getProductsByActive(id);
    }

    @Override
    public Product getByProductId(int id) {
        return productMapper.getByProductId(id);
    }

    @Override
    public Product getByTypeAndActive(int active_id, int type) {
        return productMapper.getByTypeAndActive(active_id,type);
    }
}
