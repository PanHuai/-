package com.lyl.controller;

import com.lyl.utils.SHA1;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 潘淮  on 2018/12/17.<br>
 */
@Controller
public class WxServlet{
    private final Logger logger = LoggerFactory.getLogger(WxServlet.class);

    @Value("${wx_token}")
    private String wxToken;

    @Value("${appId}")
    private String appId;

    @Value("${appsecret}")
    private String appsecret;

    @RequestMapping(value = "/wxapp",method = RequestMethod.GET)
    public void checkDo(HttpServletRequest request, HttpServletResponse response){
        String signature=request.getParameter("signature");
        String timestamp=request.getParameter("timestamp");
        String nonce=request.getParameter("nonce");
        String echostr=request.getParameter("echostr");
        try {
            String pwd = SHA1.getSHA1(wxToken, timestamp, nonce, "");
            if (pwd.equals(signature)){
                PrintWriter writer = response.getWriter();
                writer.print(echostr);
                writer.flush();
                writer.close();
            }else {
                logger.error("微信服务器配置验证失败");
            }
        } catch (Exception e) {
            logger.error("微信服务器配置验证发生错误："+e);
        }
    }

    /**
     * 获取网页授权
     */
    @RequestMapping("/wxapp/oauthDo")
    public String oauthDo(){
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +appId+
                    "&redirect_uri= http://atmyh3.natappfree.cc/wxapp/oauth_callback" +
                    "&response_type=code" +
                    "&scope=snsapi_userinfo" +
                    "&state=STATE#wechat_redirect";

           // String result = IOUtils.toString(url, "utf-8");
        return "redirect:"+url;//必须重定向，否则不能成功
    }



}
