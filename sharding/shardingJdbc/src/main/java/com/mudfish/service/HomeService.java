package com.mudfish.service;

import com.mudfish.dao.TestTableDao;
import com.mudfish.po.TestTable;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HomeService {

	@Resource
	private TestTableDao testTableDao;

	public TestTable query(int id) {
		return testTableDao.selectById(id);
	}
}
