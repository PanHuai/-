package com.lyl.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

/**
 * Created by 潘淮  on 2018/12/18.<br>
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 发送http GET 请求
     */
    public static String doGet(String urlMsg, Map<String, Object> map) throws IOException {

        // 封装请求参数
        StringBuffer sb = new StringBuffer("");
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
                sb.append("&");
            }
        }
        URL url = null;
        if (sb != null && sb.length() > 0) {
            url = new URL(urlMsg + "?" + sb.substring(0, sb.length() - 1));
            logger.info("send Http GET ask URL:" + urlMsg + "?" + sb.substring(0, sb.length() - 1));
        } else {
            url = new URL(urlMsg);
            logger.info("send Http GET ask URL:" + urlMsg);
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  // application/x-www-form-urlencoded  application/json
        // conn.addRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Connection", "keep-Alive");
        connection.setConnectTimeout(30000); // 设置连接主机超时（单位：毫秒)
        connection.setReadTimeout(30000); // 设置从主机读取数据超时（单位：毫秒)
        connection.setDoInput(true);// 设置是否从httpUrlConnection读入，默认情况下是true
        connection.setDoOutput(true);// post请求参数要放在http正文内，顾设置成true，默认是false
        connection.setUseCaches(false);// Post 请求不能使用缓存
        connection.setRequestMethod("GET");
        connection.connect();
        sb.setLength(0);
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        is.close();
        connection.disconnect();
        logger.info("response doGet msg:" + sb.toString());
        return sb.toString();
    }

    /**
     * http  POST 请求
     */
    public static String doPost(String urlMsg, Map<String, Object> map) throws IOException {
        StringBuffer sb = new StringBuffer("");
        logger.info("send Http POST ask URL:" + urlMsg);
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
                sb.append("&");
            }
            logger.info("send Http POST ask request:" + sb.substring(0, sb.length() - 1));
        }
        URL url = new URL(urlMsg);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setRequestProperty("Connection", "keep-Alive");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);

        OutputStreamWriter os = null;
        if (sb != null && sb.length() > 0) {
            os = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            os.write(sb.substring(0, sb.length() - 1));
            os.flush();
            os.close();
        }
        InputStream is = conn.getInputStream();
        InputStreamReader sr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(sr);
        String line = null;
        sb.setLength(0);
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        sr.close();
        is.close();
        conn.disconnect();
        logger.info("response doPost msg:" + sb.toString());
        return sb.toString();
    }

    /**
     * https 请求
     */
    public static String doGetHttps(String requestUrl, Map<String, Object> map)
            throws NoSuchAlgorithmException, KeyManagementException, IOException {
        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        SSLContext sslContext = SSLContext.getInstance("SSL");
        TrustManager[] tm = {new MyX509TrustManager()};
        // 初始化
        sslContext.init(null, tm, new SecureRandom());
        // 获取SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        StringBuffer sb = new StringBuffer("");
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(String.valueOf(entry.getValue()));
                sb.append("&");
            }
        }
        URL url = null;
        if (sb != null && sb.length() > 0) {
            url = new URL(requestUrl + "?" + sb.substring(0, sb.length() - 1));
            logger.info("发送https GET请求 request:"+ requestUrl+"?"+ sb.substring(0, sb.length() - 1));
        } else {
            url = new URL(requestUrl);
            logger.info("发送https GET请求 request:"+ requestUrl);
        }

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setSSLSocketFactory(ssf);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("GET");
        conn.connect();
        // 从输入流读取返回内容
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        sb.setLength(0);
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        is.close();
        conn.disconnect();
        logger.info("response doGet Https msg:" + sb.toString());
        return sb.toString();
    }

    public static String doPostHttps(String requestUrl, String data)
            throws NoSuchAlgorithmException, KeyManagementException, IOException {
        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        SSLContext sslContext = SSLContext.getInstance("SSL");
        TrustManager[] tm = {new MyX509TrustManager()};
        // 初始化
        sslContext.init(null, tm, new SecureRandom());
        // 获取SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        URL url = new URL(requestUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setSSLSocketFactory(ssf);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        if (data != null) {
            IOUtils.write(data,conn.getOutputStream(),"utf-8");
        }
        logger.info("发送https GET请求 request:"+ requestUrl+"?"+ data);
        // 从输入流读取返回内容
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuffer sb = new StringBuffer("");
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        is.close();
        conn.disconnect();
        logger.info("response do POST Https msg:" + sb.toString());
        return sb.toString();
    }

}
