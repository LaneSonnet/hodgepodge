package com.mudfish.service;

import java.util.List;

import com.mudfish.dao.TestTableDao;
import com.mudfish.dao.sharding.FundDao;
import com.mudfish.dao.sharding.OrderDao;
import com.mudfish.dao.sharding.UserDao;
import com.mudfish.po.TestTable;
import com.mudfish.vo.FundVo;
import com.mudfish.vo.OrderVo;
import com.mudfish.vo.UserVo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import io.shardingsphere.api.HintManager;

@Service
public class HomeService {

	@Resource
	private TestTableDao testTableDao;
	@Resource
	private UserDao userDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private FundDao fundDao;

	public List<FundVo> queryFundsByName(String name) {
		return fundDao.queryByName("%" + name + "%");
	}

	@Transactional
	public void add(FundVo fundVo) {
		fundDao.insert(fundVo);
		if (true) {
			throw new RuntimeException("sharding exception");
		}
	}

	public TestTable query(int id) {
		return testTableDao.selectById(id);
	}

	public void add(UserVo userVo) {
		userDao.insert(userVo);
	}

	public void add(OrderVo order) {
		HintManager hintManager = null;
		if (order.getAmount().doubleValue() > 10000) {
			hintManager = HintManager.getInstance();
			hintManager.setDatabaseShardingValue(0);
		}
		orderDao.insert(order);
		if (hintManager != null) {
			hintManager.close();
		}
	}

	public UserVo queryById(int id) {
		return userDao.queryById(id);
	}

	public List<UserVo> queryByName(String name) {
		return userDao.queryByName("%" + name + "%");
	}
}
