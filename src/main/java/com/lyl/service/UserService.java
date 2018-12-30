package com.lyl.service;

import com.lyl.model.User;

/**
 * Created by 潘淮  on 2018/12/30.<br>
 */
public interface UserService {

    public User getByOpenId(String openid);

    public int add(User user);

    public int update(User user);
}
