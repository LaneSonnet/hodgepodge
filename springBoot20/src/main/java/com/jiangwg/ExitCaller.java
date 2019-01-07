package com.jiangwg;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * Created by Mudfish on 2019/1/7 0007.
 */
@Component
public class ExitCaller implements DisposableBean {

	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean is called --------");
	}
}
