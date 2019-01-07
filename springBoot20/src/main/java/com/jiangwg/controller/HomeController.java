package com.jiangwg.controller;

import java.util.List;

import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiangwg.dao.FundDao;
import com.jiangwg.vo.FundVo;

/**
 * Created by Mudfish on 2019/1/7 0007.
 */
@RestController
public class HomeController {

	@Autowired
	private FundDao fundDao;

	@Autowired
	private ApplicationArguments args;
	@Autowired
	private Environment env;

	@RequestMapping("/queryByName")
	public List<FundVo> queryByName(String name) {
		System.out.println("获取application参数：" + args.toString());
		System.out.println("获取Environment参数：" + env.getProperty("server.port"));
		System.out.println("获取Environment参数：" + env.getActiveProfiles());
		return fundDao.queryByName("%" + name + "%");
	}

}
