package com.lyl.quartz;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lyl.model.AccessToken;
import com.lyl.service.AccessTokenService;
import com.lyl.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
@Component
public class MenuCreate {

    private final Logger logger = LoggerFactory.getLogger(MenuCreate.class);

    @Value("${localhost_url}")
    private String localhost_url;

    @Autowired
    private AccessTokenService service;

    public void task(){
        AccessToken accessToken = service.get();
        if (accessToken != null){
            String url = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken.getAccess_token();
            JsonObject object = new JsonObject();
            JsonArray array = new JsonArray();
            JsonObject js = new JsonObject();
            js.addProperty("type","view");
            js.addProperty("name","测试");
            js.addProperty("key",localhost_url);
            array.add(js);
            object.add("button",array);

            try {
                String result = HttpClientUtil.doPostHttps(url, object.toString());
                object = new JsonParser().parse(result).getAsJsonObject();
                if (!object.get("errcode").getAsString().equals("0")) {
                    logger.error("定时创建菜单信息错误：  errcode:"+object.get("errcode")+" errmsg:"+object.get("errmsg"));
                }
            } catch (NoSuchAlgorithmException e) {
                logger.error("定时创建菜单信息错误： NoSuchAlgorithmException  errcode:"+e);
            } catch (KeyManagementException e) {
                logger.error("定时创建菜单信息错误： KeyManagementException  errcode:"+e);
            } catch (IOException e) {
                logger.error("定时创建菜单信息错误： IOException  errcode:"+e);
            }
        }
    }
}
