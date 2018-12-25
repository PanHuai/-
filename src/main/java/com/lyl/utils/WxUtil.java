package com.lyl.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lyl.dto.UserInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by 潘淮  on 2018/12/18.<br>
 */
public class WxUtil {
    private static final Logger logger = LoggerFactory.getLogger(WxUtil.class);

    @Value("${wx_token}")
    private static String wxToken;

    @Value("${appId}")
    private static String appId;

    @Value("${appsecret}")
    private static String appsecret;

    /**
     * 用户同意授权后 根据微信返回的code作为换取access_token的票据
     *  String access_token;  //网页授权接口调用凭证,
     *  Long expires_in;  //access_token接口调用凭证超时时间，单位（秒）
     *  String refresh_token;  //用户刷新access_token
     *  String openid;  //用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     *  String scope;  //用户授权的作用域，使用逗号（,）分隔
     *  String errcode // 错误代码
     *  String errmsg  // 错误信息
     */
    public static JsonObject oauthCallBack(String code,String appId,String appsecret){

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+
                "&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
        try {
            String result = HttpClientUtil.doGet(url, null);
            logger.info("ask oauth2 result:"+result);
            JsonObject object = new JsonParser().parse(result).getAsJsonObject();
            if (object.get("errcode") == null){
                Map<String, Object> map = new HashMap<>();
                map.put("access_token", object.get("access_token").getAsString());
                map.put("openid", object.get("openid").getAsString());
                map.put("lang", "zh_CN");
                // 拉取用户信息
                try {
                    result = HttpClientUtil.httsRequest("https://api.weixin.qq.com/sns/userinfo", "GET", map);
                    UserInfoDto userInfoDto = (UserInfoDto) Gson.toBean(result, UserInfoDto.class);
                    logger.info("ask wx userinfo result:"+result);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                }
                return object;
            }
        } catch (IOException e) {
            logger.error("ask oauth2 error:"+e);
        }
        return null;
    }

    /**
     * 预生成订单  统一下单接口
     */
    public static String pay(){
        Map<String, String> params = new HashMap<>();
        params.put("appid", "");    //公众账号ID
        params.put("mch_id", "");   //商户号
        params.put("nonce_str", "");  //随机字符串
        params.put("body", "");      //商品描述
        params.put("out_trade_no", "");  //商户订单号
        params.put("total_fee", ""); //标价金额
        params.put("spbill_create_ip", ""); //终端IP
        params.put("notify_url", ""); //通知地址
        params.put("trade_type", ""); //交易类型
        Set<String> set = params.keySet();
        List<String> list = new ArrayList<String>(set);
        Collections.sort(list);
        StringBuffer sb = new StringBuffer("");
        for (String s : list) {
            sb.append(s + "=" + params.get(s) + "&");
        }
        sb.append("key=" + ""); // 交易密码
        return null;
    }


}
