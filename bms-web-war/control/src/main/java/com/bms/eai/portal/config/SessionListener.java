package com.bms.eai.portal.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author kul_sudhakar
 *
 */
public class SessionListener implements HttpSessionListener {

	final int HOUR = 60*60;

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setMaxInactiveInterval(HOUR*8);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
	}
	
}
