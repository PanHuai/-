package com.lyl.mapper;

import com.lyl.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
@Repository
public interface ProductMapper {

    public List<Product> get(@Param("active_id") int id);
}
