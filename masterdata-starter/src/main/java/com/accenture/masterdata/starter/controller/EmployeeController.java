package com.accenture.masterdata.starter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.masterdata.core.inEntity.BatchDeleteInput;
import com.accenture.masterdata.core.inEntity.EmployeeIn;
import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.outEntity.EmployeeOut;
import com.accenture.masterdata.employee.service.EmployeeService;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.google.common.collect.Maps;

@RestController
@Validated
@RequestMapping("/masterdata/emp")
public class EmployeeController extends baseController {

	@Autowired
	EmployeeService employee;

	@PostMapping("/get")
	public Map<String, Object> get(@RequestParam("id") Long id) {
		EmployeeOut employees =  employee.selectEmployee(tenantid, id);
		Map<String, Object> result = Maps.newHashMap();
		result.put("data", employees);
		return result;
	}
	
	@PostMapping("/getList")
	public Map<String, Object> getList(@RequestBody QueryParam params) {
		Map<String, Object> result = Maps.newHashMap();
		int count = employee.selectEmployeeCount(tenantid, params);
		List<EmployeeOut> employees = employee.selectEmployees(tenantid, params);
		
		result.put("count", count);
		result.put("list", employees);
		
		return result;
	}
	
	@PutMapping("/createOrUpdate")
	public void createOrUpdateEmployee(@RequestBody EmployeeIn params) throws Exception {
		try
		{
			employee.createOrUpdateEmployee(params);
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	@DeleteMapping("/delete")
	public int delete(@RequestParam("id") Long id) {
		//PrincipalHolder.get();
		String uid = "1";
		return employee.deleteEmployee(tenantid, eid, id);
	}

	@PostMapping("/batchDelete")
	public void batchDeleteEmployees(@RequestBody BatchDeleteInput idList) {
		//PrincipalHolder.get();
		String uid = "1";
		employee.batchDeleteEmployees(tenantid, eid, idList);
	}
}
