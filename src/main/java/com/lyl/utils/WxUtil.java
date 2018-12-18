package com.lyl.utils;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by 潘淮  on 2018/12/18.<br>
 */
public class WxUtil {

    @Value("${wx_token}")
    private String wxToken;

    @Value("${appId}")
    private String appId;

    @Value("${appsecret}")
    private String appsecret;

    /**
     * 用户同意授权后 根据微信返回的code作为换取access_token的票据
     */
    public void oauthCallBack(String code){

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid"+appId+
                "&secret=SECRET"+
                ;
        =APPID&code=CODE&grant_type=authorization_code


    }
}
