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
    public List<Product> get(int id) {
        return productMapper.get(id);
    }
}
