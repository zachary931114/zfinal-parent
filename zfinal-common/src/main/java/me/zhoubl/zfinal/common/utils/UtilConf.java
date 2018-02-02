package me.zhoubl.zfinal.common.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilConf {

    private static final Logger logger = LoggerFactory.getLogger(UtilConf.class);

    public static String COMMON = "common";
    public static String SYS = "sys";
    public static String BIZ = "biz";
    public static String DATA = "data";

    public static Properties getConfProperties(String confName) {
        Properties newProp = new Properties();
        try {
            newProp.load(UtilConf.class.getResourceAsStream("/config/" + confName + ".properties"));
        } catch (Exception ex) {
            logger.error("读取" + confName + ".properties 文件的内容错误", ex);
        }
        return newProp;
    }

}
