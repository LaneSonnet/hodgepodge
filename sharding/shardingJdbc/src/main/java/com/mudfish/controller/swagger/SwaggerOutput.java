package com.mudfish.controller.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class SwaggerOutput {

	@ApiModelProperty(notes = "对象id", required = true)
	private int id;
	@ApiModelProperty(notes = "对象名字")
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
}
