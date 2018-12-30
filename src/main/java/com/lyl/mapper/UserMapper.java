package com.lyl.mapper;

import com.lyl.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 潘淮  on 2018/12/30.<br>
 */
@Repository
public interface UserMapper {

    public User getByOpenId(@Param("openid") String openid);

    public int add(User user);

    public int update(User user);

}
