package com.mudfish.controller;

import java.util.List;

import com.mudfish.po.TestTable;
import com.mudfish.service.HomeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mudfish.vo.FundVo;
import com.mudfish.vo.OrderVo;
import com.mudfish.vo.UserVo;

import javax.annotation.Resource;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Resource
	private HomeService homeService;

	@RequestMapping("/fund/add")
	public String addFund(@RequestBody FundVo fundVo) throws Exception {
		logger.debug("请求入参【{}】", fundVo);
		homeService.add(fundVo);
		return "index";
	}

	@RequestMapping("/fund/queryByName")
	@ResponseBody
	public List<FundVo> queryFundByName(@RequestBody FundVo fundVo) throws Exception {
		logger.debug("请求入参【{}】", fundVo);
		List<FundVo> funds = homeService.queryFundsByName(fundVo.getFundName());
		logger.debug("请求出参【{}】", funds);
		return funds;
	}

	@RequestMapping("/home")
	public String home(int id) throws Exception {
		logger.debug("请求入参【{}】", id);
		TestTable test = homeService.query(id);
		logger.debug("返回参数【{}】", test);
		return "index";
	}

	@RequestMapping("/user/add")
	public String addUser(@RequestBody UserVo userVo) throws Exception {
		logger.debug("请求入参【{}】", userVo);
		homeService.add(userVo);
		return "index";
	}

	@RequestMapping("/order/add")
	public String addOrder(@RequestBody OrderVo orderVo) throws Exception {
		logger.debug("请求入参【{}】", orderVo);
		homeService.add(orderVo);
		return "index";
	}

	@RequestMapping("/user/queryById")
	@ResponseBody
	public UserVo queryById(@RequestBody UserVo userVo) throws Exception {
		logger.debug("请求入参【{}】", userVo);
		UserVo user = homeService.queryById(userVo.getId());
		logger.debug("请求出参【{}】", user);
		return user;
	}

	@RequestMapping("/user/queryByName")
	@ResponseBody
	public List<UserVo> queryByName(@RequestBody UserVo userVo) throws Exception {
		logger.debug("请求入参【{}】", userVo);
		List<UserVo> users = homeService.queryByName(userVo.getName());
		logger.debug("请求出参【{}】", users);
		return users;
	}
}
