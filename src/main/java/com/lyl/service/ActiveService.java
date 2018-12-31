package com.lyl.service;

import com.lyl.model.Active;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
public interface ActiveService {

    public Active get();

    public Active getById(int id);

    public int add(Active active);

    public int update(Active active);
}
