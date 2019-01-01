package com.mudfish.dao.sharding;

import java.util.List;

import com.mudfish.vo.FundVo;

public interface FundDao {

	void insert(FundVo fund);

	List<FundVo> queryByName(String fundName);
}