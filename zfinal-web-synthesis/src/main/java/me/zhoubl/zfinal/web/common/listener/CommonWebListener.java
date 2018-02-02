package me.zhoubl.zfinal.web.common.listener;


import me.zhoubl.zfinal.common.CommonConfig;
import me.zhoubl.zfinal.common.utils.UtilConf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



/**
 * Created by zhoubl on 2017/2/16.
 */
public class CommonWebListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		CommonConfig.commonMap = new ConcurrentHashMap<String, String>((Map) UtilConf.getConfProperties(UtilConf.COMMON));
		CommonConfig.sysMap = new ConcurrentHashMap<String, String>((Map) UtilConf.getConfProperties(UtilConf.SYS));
		CommonConfig.bizMap = new ConcurrentHashMap<String, String>((Map) UtilConf.getConfProperties(UtilConf.BIZ));
		CommonConfig.dataMap = new ConcurrentHashMap<String, String>((Map) UtilConf.getConfProperties(UtilConf.DATA));
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}
}
