package com.lyl.controller;

import com.google.gson.JsonObject;
import com.lyl.model.Order;
import com.lyl.service.OrderService;
import com.lyl.utils.SHA1;
import com.lyl.utils.StringUtils;
import com.lyl.utils.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 潘淮  on 2018/12/17.<br>
 */
@Controller
public class WxController {
    private final Logger logger = LoggerFactory.getLogger(WxController.class);

    @Value("${wx_token}")
    private String wxToken;

    @Value("${appId}")
    private String appId;

    @Value("${appsecret}")
    private String appsecret;
    @Value("${localhost_url}")
    private String localhost_url;
    @Autowired
    private OrderService orderService;

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
    @RequestMapping(value = "/wxapp/oauthDo",method = RequestMethod.POST)
    public String oauthDo() throws UnsupportedEncodingException {
        String callback = URLEncoder.encode(localhost_url+"/wxapp/oauth_callback", "UTF-8");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +appId+
                    "&redirect_uri="+callback+
                    "&response_type=code" +
                    "&scope=snsapi_userinfo" +
                    "&state=myInfo#wechat_redirect";

           // String result = IOUtils.toString(url, "utf-8");
        return "redirect:"+url;//必须重定向，否则不能成功
    }

    /**
     * 通过code换取网页授权access_token
     */
    @RequestMapping(value = "/wxapp/oauth_callback",method = RequestMethod.POST)
    public void oauthCallback(HttpServletRequest request){
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if (StringUtils.isNotBlank(code)){
            JsonObject object = WxUtil.oauthCallBack(code,appId,appsecret);


        }

    }


    /**
     * 微信支付
     */
    @RequestMapping(value = "/api/user/wx_h5/pay",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> wxPay_H5(HttpServletRequest request){
        int orderId = Integer.valueOf(request.getParameter("orderId"));
        Order order = orderService.getById(orderId);
        if (order == null){
            throw new RuntimeException("订单信息为空");
        }
        Map<String, Object> map = new HashMap<>();
        try {
            map = WxUtil.pay_h5(request, localhost_url+"/wxapp/h5/notif_callback", 1,order.getNo());
            map.put("state", "success");
            logger.info(map.toString());
        } catch (Exception e) {
            logger.error("wxpp/h5/pay error:"+e);
            map.put("state", "failed");
            map.put("msg", e);
        }
        return map;
    }

    /**
     * 微信支付回调
     */
    @RequestMapping("wxapp/h5/notif_callback")
    public void callBack_h5(final HttpServletRequest request, HttpServletResponse response){
        try {
            WxUtil.callBack_h5(request, new WxUtil.CallBack() {
                @Override
                public int callBack(String pay_no, String no) {
                    logger.info("wxapp/h5/notif_callback go ...pay_no:"+pay_no+",no:"+no);
                    int result = orderService.wxPaySuccess(pay_no, no);
                    logger.info("wxapp/h5/notif_callback  result:"+request);
                    return result;
                }
            },response);
        } catch (Exception e) {
            logger.error("wxapp/h5/notif_callback error:"+e);
        }
    }




}
