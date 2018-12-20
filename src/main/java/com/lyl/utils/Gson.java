package com.lyl.utils;

import com.google.gson.GsonBuilder;

/**
 * Created by 潘淮  on 2018/12/20.<br>
 */
public class Gson{

    /**
     * 将bean转换成Json字符串
     */
    public static String toJson(Object bean){
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(bean);
    }

    /**
     * 将json字符串转换成对象
     */
    public static Object toBean(String json,Class<?> bean){
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(json, bean);
    }
}
