package me.zhoubl.zfinal.web.common.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * Created by zhoubl on 2017/2/18.
 */
public class CommonShiroSessionListener implements SessionListener {

	@Override
	public void onStart(Session session) {
		System.out.println("-----------onStart");
		
	}

	@Override
	public void onStop(Session session) {
		System.out.println("-----------onStop");
		
	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("-----------onExpiration");
		
	}
}
