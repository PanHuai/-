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

    public List<Product> getOrigByActive(@Param("active_id") int id);

    public List<Product> getProductsByActive(@Param("active_id") int id);

    public Product getByProductId(@Param("id") int id);

    public Product getByTypeAndActive(@Param("active_id") int id,@Param("type") int type);
}
