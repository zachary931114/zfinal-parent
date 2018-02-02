package me.zhoubl.zfinal.web.common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CommonHttpSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("-----------sessionCreated");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("-----------sessionDestroyed");
		
	}

}
