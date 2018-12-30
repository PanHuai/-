package com.lyl.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lyl.dto.UserInfoDto;
import com.lyl.model.User;
import com.lyl.service.UserService;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by 潘淮  on 2018/12/18.<br>
 */
@Component
public class WxUtil {
    private static final Logger logger = LoggerFactory.getLogger(WxUtil.class);

    @Value("${wx_token}")
    private String wxToken;

    @Value("${appId}")
    private String appId;

    @Value("${appsecret}")
    private String appsecret;

    @Value("${mch_key}")
    private String mch_key;

    @Value("${mch_id}")
    private String mch_id;

    @Autowired
    private UserService userService;

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
    public void oauthCallBack(String code,HttpServletRequest request) throws Exception{

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+
                "&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";

            String result = HttpClientUtil.doGet(url, null);
            logger.warn("根据code获取授权凭证access_token  result:"+result);
            JsonObject object = new JsonParser().parse(result).getAsJsonObject();
            if (object.get("errcode") == null){
                String openid = object.get("openid").getAsString();
                User user = userService.getByOpenId(openid);
                if (user == null){
                    Map<String, Object> map = new HashMap<>();
                    map.put("openid", openid);
                    map.put("access_token", object.get("access_token").getAsString());
                    map.put("lang", "zh_CN");

                        result = HttpClientUtil.doGetHttps("https://api.weixin.qq.com/sns/userinfo", map);
                        logger.warn("授权获取用户基本信息 result:"+result);
                        UserInfoDto userInfoDto = (UserInfoDto) Gson.toBean(result, UserInfoDto.class);
                    user = new User();
                    user.setOpenid(userInfoDto.getOpenid());
                    user.setCity(userInfoDto.getCity());
                    user.setSex(userInfoDto.getSex());
                    user.setCreateTime(LocalDateTime.now());
                    user.setLogo(userInfoDto.getHeadimgurl());
                    user.setUnionid(userInfoDto.getUnionid());
                    user.setUserName(userInfoDto.getNickname());
                    user.setVersion(0);
                    user.setUpdateTime(LocalDateTime.now());
                    user.setTel("");
                    userService.add(user);
                    logger.warn("授权用户用户编号:"+user.getId());
                }
                HttpSession session = request.getSession();
                session.setAttribute("openid",openid);
                session.setMaxInactiveInterval(30*60);  // 单位/秒
            }else {
                throw new RuntimeException("根据code获取授权凭证access_token发生");
            }
    }

    /**
     * 预生成订单  统一下单接口
     */
    public Map<String, Object> pay_h5(HttpServletRequest request,String notify_url,int payMoney,String no,String openid) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("appid", appId);    //公众账号ID
        param.put("mch_id", mch_id);   //商户号
        param.put("nonce_str", RandomString.getInstance().generate());  //随机字符串
        param.put("body", URLDecoder.decode("淮安精英人力资源订单在线支付","UTF-8"));      //商品描述
        param.put("out_trade_no", no);  //商户订单号
        param.put("openid", openid);  //商户订单号
        param.put("total_fee", payMoney); //标价金额
        param.put("spbill_create_ip", IPUtil.get(request)); //终端IP
        param.put("notify_url", notify_url); //通知地址
        param.put("trade_type", "JSAPI"); //交易类型
        Set<String> set = param.keySet();
        List<String> list = new ArrayList<String>(set);
        Collections.sort(list);
        StringBuffer sb = new StringBuffer("");
        for (String s : list) {
            sb.append(s + "=" + param.get(s) + "&");
        }
        sb.append("key=" + mch_key); // 交易密码
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        param.put("sign", sign);
        StringBuffer xml = new StringBuffer("");
        xml.append("<xml>");
        xml.append("<appid><![CDATA["+param.get("appid")+"]]></appid>");
        xml.append("<mch_id><![CDATA["+param.get("mch_id")+"]]></mch_id>");
        xml.append("<sign><![CDATA["+param.get("sign")+"]]></sign>");
        xml.append("<openid><![CDATA["+param.get("openid")+"]]></openid>");
        xml.append("<nonce_str><![CDATA["+param.get("nonce_str")+"]]></nonce_str>");
        xml.append("<body><![CDATA["+param.get("body")+"]]></body>");
        xml.append("<out_trade_no><![CDATA["+param.get("out_trade_no")+"]]></out_trade_no>");
        xml.append("<total_fee><![CDATA["+param.get("total_fee")+"]]></total_fee>");
        xml.append("<spbill_create_ip><![CDATA["+param.get("spbill_create_ip")+"]]></spbill_create_ip>");
        xml.append("<notify_url><![CDATA["+param.get("notify_url")+"]]></notify_url>");
        xml.append("<trade_type><![CDATA["+param.get("trade_type")+"]]></trade_type>");
        xml.append("</xml>");
        URL url = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
        logger.info("统一下单 xml请求参数："+xml.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(30000);  // 单位 毫秒
        conn.setReadTimeout(30000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        IOUtils.write(xml.toString(), conn.getOutputStream(), "utf-8");
        InputStream is = conn.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        Map<String, String> map = new HashMap<>();
        //得到xml根元素
        Element root = document.getRootElement();
        //得到根元素的所有子节点
        List<Element> eList = root.elements();
        for (Element element : eList) {
            map.put(element.getName(), element.getText());
        }
        if (is != null){
            is.close();
        }
        conn.disconnect();
        param.clear();
        logger.info("pay_h5 result:"+map.toString());
        if (map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")){
            String prepay_id = map.get("prepay_id");
            String nonce_str = map.get("nonce_str");
            param.put("appId", appId);
            param.put("timeStamp", System.currentTimeMillis()+"");
            param.put("nonceStr", nonce_str);
            param.put("package", "prepay_id="+prepay_id);
            param.put("signType", "MD5");
            set = param.keySet();
            list = new ArrayList<String>(set);
            Collections.sort(list);
            sb = new StringBuffer("");
            for (String s : list) {
                sb.append(s + "=" + param.get(s) + "&");
            }
            sb.append("key=" + mch_key); // 交易密码
            String paySign = MD5Util.MD5(sb.toString(), "UTF-8").toUpperCase();
            param.put("paySign", paySign);
        }else {
            logger.error("wx pay/unifiedorder error   return_msg:"+map.get("return_msg")+" err_code:"+map.get("err_code"));
            throw new RuntimeException("h5订单支付失败，出现异常");
        }
        return param;
    }

    /**
     * h5支付回调
     */
    public void callBack_h5(HttpServletRequest request, CallBack callBack, HttpServletResponse response) throws Exception {
        InputStream is = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        Map<String, String> map = new HashMap<>();
        //得到xml根元素
        Element root = document.getRootElement();
        //得到根元素的所有子节点
        List<Element> eList = root.elements();
        for (Element element : eList) {
            map.put(element.getName(), element.getText());
        }
        if (map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")){
            //map.get("is_subscribe");  // 用户是否关注公众账号，Y-关注，N-未关注
            String pay_no = map.get("transaction_id");  // 微信支付订单号
            String no = map.get("out_trade_no");
            int result = callBack.callBack(pay_no, no);
            if (result==0) {
                StringBuffer sb = new StringBuffer();
                sb.append("<xml>");
                sb.append("<return_code><![CDATA[SUCCESS]]></return_code>");
                sb.append("<return_msg><![CDATA[OK]]></return_msg>");
                sb.append("</xml>");
                PrintWriter writer = response.getWriter();
                writer.print(URLEncoder.encode(sb.toString(), "UTF-8"));
                logger.info("wx pay/unifiedorder callback success transaction_id:"+pay_no+" out_trade_no"+no);
            }else {
                logger.error("wx pay/unifiedorder callback success but set failed transaction_id:"+pay_no+" out_trade_no"+no);
            }
        }else {
            logger.error("wx pay/unifiedorder callback error   return_msg:"+map.get("return_msg")+" err_code:"+map.get("err_code"));
            throw new RuntimeException("h5订单回调失败，出现异常");
        }
    }

    /**
     * 回调处理方法
     */
    public static abstract class CallBack{
        public abstract int callBack(String pay_no,String no);
    }
}
