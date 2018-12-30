package com.lyl.service.Impl;

import com.lyl.mapper.UserMapper;
import com.lyl.model.User;
import com.lyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 潘淮  on 2018/12/30.<br>
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByOpenId(String openid) {
        return userMapper.getByOpenId(openid);
    }

    @Override
    @Transactional
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    @Transactional
    public int update(User user) {
        return userMapper.update(user);
    }
}
