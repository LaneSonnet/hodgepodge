package com.mudfish.controller.swagger;

import io.swagger.annotations.ApiModelProperty;

public class SwaggerInput {

	@ApiModelProperty(notes = "对象id", required = true)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
