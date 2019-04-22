package org.tyaa.javaee.gae.pps2716;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;

public class ObjectifyBootstrapper implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ObjectifyService.init();
		ObjectifyService.register(User.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	
}
