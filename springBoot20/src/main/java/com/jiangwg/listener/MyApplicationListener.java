package com.jiangwg.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by Mudfish on 2019/1/7 0007.
 */
public class MyApplicationListener implements ApplicationListener<ApplicationStartingEvent>{

	@Override
	public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
			System.out.println("MyApplicationListener-------" + applicationStartingEvent.getSpringApplication().toString());

		}
}
