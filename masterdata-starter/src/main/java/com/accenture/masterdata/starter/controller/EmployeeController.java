package com.accenture.masterdata.starter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.masterdata.core.inEntity.EmployeeIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.outEntity.EmployeeOut;
import com.accenture.masterdata.employee.service.EmployeeService;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.google.common.collect.Maps;

@RestController
@Validated
@RequestMapping("/masterdata/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employee;

	@GetMapping("/get")
	public EmployeeOut get(@RequestParam("id") int id) {
		return employee.selectEmployeeDetail(id);
	}
	
	@GetMapping("/getList")
	public Map<String, Object> getList(QueryParam params) {
		Map<String, Object> result = Maps.newHashMap();
		int count = employee.selectEmployeeCount(params);
		List<EmployeeOut> employees = employee.selectEmployees(params);
		
		result.put("count", count);
		result.put("list", employees);
		
		return result;
	}
	
	@PostMapping("/add")
	public int insert(@RequestParam("org_data") EmployeeIn params) {
		return employee.addEmployee(params);
	}
	
	@PutMapping("/save")
	public int update(@RequestParam("org_data") EmployeeIn params) {
		return employee.saveEmployee(params);
	}
	
	@DeleteMapping("/delete")
	public int delete(@RequestParam("id")int id) {
		return employee.deleteEmployee(id);
	}
	
}
