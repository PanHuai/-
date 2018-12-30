package com.lyl.quartz;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lyl.model.AccessToken;
import com.lyl.service.AccessTokenService;
import com.lyl.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
@Component
public class AccessTokenJob {

    @Value("${appId}")
    private String appId;

    @Value("${appsecret}")
    private String appsecret;

    @Value("${check_access_token}")
    private String check_access_token;

    @Autowired
    private AccessTokenService service;

    public void task(){
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> map = new HashMap<>();
        map.put("appid", appId);
        map.put("secret", appsecret);
        map.put("grant_type", "client_credential");
        try {
            AccessToken accessToken = service.get();
            if (accessToken == null){
                accessToken = new AccessToken();
                String result = HttpClientUtil.doGetHttps(url, map);
                JsonObject object = new JsonParser().parse(result).getAsJsonObject();
                String access_token = object.get("access_token").getAsString();
                Long expires_in = object.get("expires_in").getAsLong();
                accessToken.setAccess_token(access_token);
                accessToken.setExpires_in(expires_in);
                accessToken.setBeginTime(LocalDateTime.now());
                service.add(accessToken);
            }else {
                LocalDateTime beginTime = accessToken.getBeginTime();
                long between = Duration.between(beginTime, LocalDateTime.now()).toMillis();
                if (between <= (Long.valueOf(check_access_token))) {
                    String result = HttpClientUtil.doGetHttps(url, map);
                    JsonObject object = new JsonParser().parse(result).getAsJsonObject();
                    String access_token = object.get("access_token").getAsString();
                    Long expires_in = object.get("expires_in").getAsLong();
                    accessToken.setAccess_token(access_token);
                    accessToken.setExpires_in(expires_in);
                    accessToken.setBeginTime(LocalDateTime.now());
                    service.updata(accessToken);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
