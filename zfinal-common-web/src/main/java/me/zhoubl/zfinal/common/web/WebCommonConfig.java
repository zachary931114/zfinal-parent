package me.zhoubl.zfinal.common.web;

import me.zhoubl.zfinal.common.page.DataTableJson;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by zhoubl on 2017/6/7.
 */
public class WebCommonConfig {

    public static final String USERINFO = "userInfo";
    public static final String ROLEINFO = "roleInfo";

    public static final String LISTJSONPATH = "/WEB-INF/listjson";

    public static Map<String, DataTableJson> dataTableJsonMap = new ConcurrentHashMap<String, DataTableJson>();

    public static final String SQLUSERID = "@USERID";

    public static final String UPLOADFILEDEFAULTPATH = "upload/file";

    public static final String SEPARATOR = "@^@";
}
