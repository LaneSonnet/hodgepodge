package com.mudfish.dao.sharding;

import java.util.List;

import com.mudfish.vo.UserVo;

public interface UserDao {

	void insert(UserVo userVo);

	UserVo queryById(int id);

	List<UserVo> queryByName(String name);
}