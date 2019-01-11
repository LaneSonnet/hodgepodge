package com.jiangwg;

import java.util.List;

import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 全局错误处理器
 */
@RequestMapping("/error")
@Controller
public class MyErrorController extends AbstractErrorController{

	public MyErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	public MyErrorController(ErrorAttributes errorAttributes,
			List<ErrorViewResolver> errorViewResolvers) {
		super(errorAttributes, errorViewResolvers);
	}

	@Override
	public String getErrorPath() {
		return null;
	}
}
