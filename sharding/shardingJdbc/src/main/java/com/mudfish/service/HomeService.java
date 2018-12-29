package com.mudfish.service;

import java.util.List;

import com.mudfish.dao.TestTableDao;
import com.mudfish.dao.sharding.UserDao;
import com.mudfish.po.TestTable;
import com.mudfish.vo.UserVo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HomeService {

/*	@Resource
	private TestTableDao testTableDao;*/
	@Resource
	private UserDao userDao;

/*
	public TestTable query(int id) {
		return testTableDao.selectById(id);
	}
*/

	public void add(UserVo userVo) {
		userDao.insert(userVo);
	}

	public UserVo queryById(int id) {
		return userDao.queryById(id);
	}

	public List<UserVo> queryByName(String name) {
		return userDao.queryByName("%" + name + "%");
	}
}
