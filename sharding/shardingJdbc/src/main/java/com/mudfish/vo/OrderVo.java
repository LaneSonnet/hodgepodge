package com.mudfish.vo;

import java.math.BigDecimal;

/**
 * Created by Mudfish on 2018/12/30 0030.
 */
public class OrderVo {

	private int id;
	private int user;
	private int num;
	private BigDecimal amount;
	private String comment;

	@Override
	public String toString() {
		return "OrderVo{" +
				"id=" + id +
				", user=" + user +
				", num=" + num +
				", amount=" + amount +
				", comment='" + comment + '\'' +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
