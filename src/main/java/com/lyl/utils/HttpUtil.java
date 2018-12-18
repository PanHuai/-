package com.lyl.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by 潘淮  on 2018/12/18.<br>
 */
public class HttpUtil {


    /**
     * 发送http GET 请求
     */
    public static void doGet(String urlMsg, Map<String,Object> map) throws IOException {

        // 封装请求参数
        StringBuffer sb = new StringBuffer("");
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
        }
        URL url = null;
        if (sb != null && sb.length() > 0) {
            url = new URL(urlMsg + "?" + sb.substring(0, sb.length() - 1));
        } else {
            url = new URL(urlMsg);
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("connection", "keep-Alive");
        connection.setRequestMethod("GET");
        connection.connect();





    }
}
