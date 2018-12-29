package com.mudfish.controller;

import com.mudfish.po.TestTable;
import com.mudfish.service.HomeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mudfish.vo.UserVo;

import javax.annotation.Resource;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Resource
	private HomeService homeService;

	@RequestMapping("/home")
	public String tenpayOpenAcco(int id) throws Exception {
		logger.debug("请求入参【{}】", id);
		TestTable test = homeService.query(id);
		logger.debug("返回参数【{}】", test);
		return "index";
	}

}
