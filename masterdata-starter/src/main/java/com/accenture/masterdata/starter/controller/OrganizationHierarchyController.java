package com.accenture.masterdata.starter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchyOut;
import com.accenture.masterdata.organization.service.OrganizationService;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.accenture.smsf.framework.starter.web.principal.PrincipalHolder;
import com.google.common.collect.Maps;

@RestController
@Validated
@RequestMapping("/masterdata/orgHierarchy")
public class OrganizationHierarchyController {
	@Autowired
	OrganizationService organization;

	@PostMapping("/get")
	public Map<String, Object> get(@RequestParam("id") Long id) {
		OrganizationHierarchyOut hierarchies = organization.selectOrganizationHierarchy(id);
		Map<String, Object> result = Maps.newHashMap();
		result.put("data", hierarchies);
		return result;
	}
	
	@PostMapping("/getList")
	public Map<String, Object> getList(@RequestBody QueryParam param) {
		Map<String, Object> result = Maps.newHashMap();
		int count = organization.selectOrganizationHierCount(param);
		List<OrganizationHierarchyOut> hierarchies = organization.selectOrganizationHierarchys(param);
		
		result.put("count", count);
		result.put("list", hierarchies);
		
		return result;
	}
	
	@PostMapping("/add")
	public int insert(@RequestBody OrganizationHierarchyIn params) {

//		//EID
//		PrincipalHolder.get();
//		// Tenant id
//		TenantHolder.get();
		return organization.addOrganizationHierarchy(params);
	}
	
	@PutMapping("/save")
	public int update(@RequestBody OrganizationHierarchyIn params) {
		return organization.saveOrganizationHierarchy(params);
	}
	
	@DeleteMapping("/delete")
	public int delete(@RequestParam("id") Long id) {
		String eid = "1";
		//PrincipalHolder.get();
		return organization.deleteOrganizationHierarchy(Long.parseLong(eid),id);
	}
	
}
