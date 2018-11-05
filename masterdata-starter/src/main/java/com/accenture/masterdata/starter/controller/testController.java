package com.accenture.masterdata.starter.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accenture.masterdata.core.inEntity.QueryParm;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/masterdata/test")
@Api("测试API")
public class testController {

	@ApiOperation(value = "测试", notes = "测试Controller")
    @ApiImplicitParam(name = "id", value = "测试主键", paramType = "path", required = true, dataType = "QueryParm")
	@PostMapping("/test")
	public String test(@RequestBody QueryParm id) {
		return "nello";
	}
}
