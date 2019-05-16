package com.mudfish.controller.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@Controller
@RequestMapping(value = "/stat")
@Api(hidden = true)
public class SwaggerController {

	@ResponseBody
	@RequestMapping(value = "/helloworld", method = RequestMethod.GET)
	@ApiOperation(value = "Swagger的世界", notes = "测试HelloWorld", tags = "aaaaa",  produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "nickname", value = "用户名")})
	public String helloWorld( @RequestParam String nickname) {
		return "Hello world, " + nickname;
	}

	@ResponseBody
	@RequestMapping(value = "/objectio", method = RequestMethod.POST)
	@ApiOperation(value = "Swagger的ObjectIO", tags = "bbbbbb",  produces = "application/json")
	public SwaggerOutput objectIo(@RequestBody SwaggerInput input) {
		SwaggerOutput output = new SwaggerOutput();
		output.setId(input.getId());
		output.setName("Swagger");
		return output;
	}
}
