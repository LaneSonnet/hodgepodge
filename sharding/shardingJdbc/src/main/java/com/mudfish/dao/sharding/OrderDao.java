package com.mudfish.dao.sharding;

import com.mudfish.po.TestTable;
import com.mudfish.vo.OrderVo;

public interface OrderDao {

	void insert(OrderVo order);
}