package com.lyl.mapper;

import com.lyl.model.AccessToken;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
@Repository
public interface AccessTokenMapper {

    public int add(AccessToken accessToken);

    public int updata(AccessToken accessToken);

    public AccessToken get();
}
