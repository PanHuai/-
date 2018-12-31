package com.lyl.listener;

import com.lyl.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 在拦截点执行前拦截，如果返回true则不执行拦截点后的操作（拦截成功）
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/index");
            return false;
        }
        session.setMaxInactiveInterval(30*60);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 在处理过程中，执行拦截
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 执行完毕，返回前拦截
    }
}
