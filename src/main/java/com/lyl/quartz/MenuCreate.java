package com.lyl.quartz;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lyl.utils.HttpClientUtil;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
public class MenuCreate {

    public void task(){
        String url = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        JsonObject js = new JsonObject();
        js.addProperty("type","view");
        js.addProperty("name","测试");
        js.addProperty("key","http://zh337s.natappfree.cc");
        array.add(js);
       object.add("button",array);

        try {
            HttpClientUtil.doPostHttps(url, object.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
