package com.lyl.mapper;

import com.lyl.model.Active;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
@Repository
public interface ActiveMapper {

    public Active get();

    public Active getById(@Param("id") int id);

    public int add(Active active);

    public int update(Active active);
}
