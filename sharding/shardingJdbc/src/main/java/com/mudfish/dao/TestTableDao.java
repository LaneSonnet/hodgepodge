package com.mudfish.dao;

import com.mudfish.po.TestTable;

public interface TestTableDao {
    TestTable selectById(int id);
}