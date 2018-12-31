package com.lyl.service.Impl;

import com.lyl.mapper.ActiveMapper;
import com.lyl.model.Active;
import com.lyl.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
@Service("ActiveService")
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    private ActiveMapper activeMapper;
    @Override
    public Active get() {
        return activeMapper.get();
    }

    @Override
    public Active getById(int id) {
        return activeMapper.getById(id);
    }

    @Override
    @Transactional
    public int add(Active active) {
        return activeMapper.add(active);
    }

    @Override
    @Transactional
    public int update(Active active) {
        return activeMapper.update(active);
    }
}
