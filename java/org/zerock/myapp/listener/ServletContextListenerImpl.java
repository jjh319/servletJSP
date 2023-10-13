package org.zerock.myapp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
	

	// 현재의 웹어플리케이션이 중지되는 이벤트를 감지
	public void contextDestroyed(ServletContextEvent sce)  {
		log.trace("contextDestroyed({}) invoked.", sce);
		
    } // contextDestroyed

	// 현재의 웹어플리케이션이 시작되는 이벤트를 감지
	public void contextInitialized(ServletContextEvent sce)  {
		log.trace("contextInitialized({}) invoked.", sce);
		
    } // contextInitialized
	
} // end class
