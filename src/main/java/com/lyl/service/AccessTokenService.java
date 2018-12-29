package com.lyl.service;

import com.lyl.model.AccessToken;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
public interface AccessTokenService {

    public int add(AccessToken accessToken);

    public int updata(AccessToken accessToken);

    public AccessToken get();
}
