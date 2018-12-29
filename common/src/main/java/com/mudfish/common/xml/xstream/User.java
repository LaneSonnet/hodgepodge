package com.mudfish.common.xml.xstream;

import com.mudfish.common.xml.xstream.converter.DateConverter;
import com.thoughtworks.xstream.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by JiangWeiGen on 2018/11/18 0018.
 */
@XStreamAlias("user")
@XStreamConverter(DateConverter.class)
public class User {

	private Date birthday;
	@XStreamAlias("first_name")
	private String name;
	@XStreamOmitField
	private Integer age;
	//    @XStreamAsAttribute
	private List<Customer> customer;

	public String getName() {
		return name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User{" +
				"birthday=" + birthday +
				", name='" + name + '\'' +
				", age=" + age +
				", customer=" + customer +
				'}';
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
}
