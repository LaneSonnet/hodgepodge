package com.jiangwg.dao;

import java.util.List;

import com.jiangwg.vo.FundVo;

public interface FundDao {

	void insert(FundVo fund);

	List<FundVo> queryByName(String fundName);
}