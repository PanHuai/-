package com.lyl.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by 潘淮  on 2018/12/30.<br>
 */
public class SessionTimeListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // session创建时做的事情

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        // session销毁或者失效时做的事情
        HttpSession session = httpSessionEvent.getSession();
        String id=session.getId()+session.getCreationTime();
    }
}
