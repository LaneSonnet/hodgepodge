package com.mudfish.po;

public class TestTable {

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TestTableDao{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
