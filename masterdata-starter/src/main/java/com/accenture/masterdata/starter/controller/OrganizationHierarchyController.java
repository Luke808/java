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

import com.accenture.masterdata.core.inEntity.BatchDeleteInput;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchy;
import com.accenture.masterdata.organization.service.OrganizationHierarchyService;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.accenture.smsf.framework.starter.web.principal.TenantHolder;
import com.google.common.collect.Maps;

@RestController
@Validated
@RequestMapping("/masterdata/orgHierarchy")
public class OrganizationHierarchyController{
	@Autowired
	OrganizationHierarchyService organization;

	@PostMapping("/get")
	public Map<String, Object> get(@RequestParam("id") Long id) {
		OrganizationHierarchy hierarchies = organization.selectOrganizationHierarchy(id);
		Map<String, Object> result = Maps.newHashMap();
		result.put("data", hierarchies);
		return result;
	}
	
	@PostMapping("/getList")
	public Map<String, Object> getList(@RequestBody QueryParam param) {
		Map<String, Object> result = Maps.newHashMap();
		int count = organization.selectOrganizationHierCount(param);
		List<OrganizationHierarchy> hierarchies = organization.selectOrganizationHierarchys(param);
		
		result.put("count", count);
		result.put("list", hierarchies);
		
		return result;
	}
	
	@PutMapping("/createOrUpdate")
	public void createOrUpdateOrganizationHierarchy(@RequestBody OrganizationHierarchy params) throws Exception {
		try
		{
			organization.createOrUpdateOrganizationHierarchy(params);
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	@DeleteMapping("/delete")
	public int delete(@RequestParam("id") Long id) {
		return organization.deleteOrganizationHierarchy(id);
	}
	
	@PostMapping("/batchDelete")
	public void batchDeleteOrganizationHierarchy(@RequestBody BatchDeleteInput idList) {
		organization.batchDeleteOrganizationHierarchy(idList);
	}
	
}
