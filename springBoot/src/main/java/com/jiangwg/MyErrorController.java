package com.jiangwg;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局错误处理器
 */
@RequestMapping("/error")
@Controller
public class MyErrorController extends BasicErrorController {

	private static final Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);

	@Autowired
	public MyErrorController(ErrorAttributes errorAttributes,
			ServerProperties serverProperties,
			List<ErrorViewResolver> errorViewResolvers) {
		super(errorAttributes, serverProperties.getError(), errorViewResolvers);
	}

	@RequestMapping(produces = {"text/html"})
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
		HttpStatus status = super.getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(
				this.getErrorAttributes(request, this.isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
		return modelAndView == null ? new ModelAndView("myError.html", model) : modelAndView;
	}

	@RequestMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("errorCode", "9999");
		body.put("errorMsg", "错了");
		HttpStatus status = this.getStatus(request);
		logger.info("-------------------controller捕获异常【{}】", status);
		return new ResponseEntity(body, status);
	}

}
