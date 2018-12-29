package com.lyl.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
public class LogUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger("com.lyl");

    public static void info(String msg){
        LOGGER.info(msg);
    }

    public static void error(String msg){
        LOGGER.error(msg);
    }

}
