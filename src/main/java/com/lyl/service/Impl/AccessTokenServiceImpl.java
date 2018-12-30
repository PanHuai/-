package com.lyl.service.Impl;

import com.lyl.mapper.AccessTokenMapper;
import com.lyl.model.AccessToken;
import com.lyl.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
@Service("AccessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private AccessTokenMapper mapper;
    @Override
    @Transactional
    public int add(AccessToken accessToken) {
        return mapper.add(accessToken);
    }

    @Override
    @Transactional
    public int updata(AccessToken accessToken) {
        return mapper.updata(accessToken);
    }

    @Override
    public AccessToken get() {
        return mapper.get();
    }
}
