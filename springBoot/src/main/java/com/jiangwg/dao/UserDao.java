package com.jiangwg.dao;

import java.util.List;

import com.jiangwg.po.UserPo;

public interface UserDao {

	List<UserPo> selectByName(String name);
}
