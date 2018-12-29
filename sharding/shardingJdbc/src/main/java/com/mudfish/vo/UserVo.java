package com.mudfish.vo;

public class UserVo {

	private int id;
	private String name;
	private int age;
	private String birthday;
	private int area;

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "UserVo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", birthday='" + birthday + '\'' +
				", area=" + area +
				'}';
	}
}
